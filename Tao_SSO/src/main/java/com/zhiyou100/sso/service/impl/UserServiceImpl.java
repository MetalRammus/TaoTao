package com.zhiyou100.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import com.zhiyou100.mapper.TbUserMapper;
import com.zhiyou100.model.TbUser;
import com.zhiyou100.model.TbUserExample;
import com.zhiyou100.model.TbUserExample.Criteria;
import com.zhiyou100.sso.service.UserService;
import com.zhiyou100.utils.CookieUtils;
import com.zhiyou100.utils.JsonUtils;
import com.zhiyou100.utils.TaotaoResult;

/**
 * |
 * 
 * @author 19229
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private TbUserMapper tbUserMapper;
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 
	 */
	@Override
	public TaotaoResult checkData(String content, Integer type) {
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		// 1 username 2 phone 3 email
		if (type == 1) {
			criteria.andUsernameEqualTo(content);
		} else if (type == 2) {
			criteria.andPhoneEqualTo(content);
		} else {
			criteria.andEmailEqualTo(content);
		}
		// 查询 无重复，true
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.ok(true);
		}
		return TaotaoResult.ok(false);
	}

	/**
	 * 注册
	 */
	@Override
	public TaotaoResult createUser(TbUser user) {
		// 补全
		user.setUpdated(new Date());
		user.setCreated(new Date());
		// 加密
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		tbUserMapper.insert(user);
		return TaotaoResult.ok();
	}

	/**
	 * 登录
	 */
	@Override
	public TaotaoResult userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response) {
		// 查
		TbUserExample example = new TbUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return TaotaoResult.build(400, "用户名错误");
		}
		TbUser user = list.get(0);
		// 比对密码
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
			return TaotaoResult.build(400, "密码错误");
		}
		String token = UUID.randomUUID().toString();
		// 清空密码，安全性 令牌不保存密码
		user.setPassword(null);

		redisTemplate.opsForValue().set("REDIS_USER_SESSION_KEY" + ":" + token, JsonUtils.objectToJson(user));
		redisTemplate.expire("REDIS_USER_SESSION_KEY" + ":" + token, 1800, TimeUnit.SECONDS);

		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效。
		//设置Cookie的值 不设置生效时间默认浏览器关闭即失效,也不编码
		CookieUtils.setCookie(request, response, "TT_TOKEN", token);
//		Cookie cookie = new Cookie("TT_TOKEN", token);
//		cookie.setDomain("taotao.com");
//		cookie.setPath("/");
//		response.addCookie(cookie);
		return TaotaoResult.ok(token);
	}

	/**
	 * 通过令牌查找用户信息
	 */
	@Override
	public TaotaoResult getUserByToken(String token) {
		String json = (String) redisTemplate.opsForValue().get("REDIS_USER_SESSION_KEY" + ":" + token);
		if (StringUtils.isBlank(json)) {
			return TaotaoResult.build(400, "session过期，重新登录");
		}
		// 更新过期时间
		redisTemplate.expire("REDIS_USER_SESSION_KEY" + ":" + token, 1800, TimeUnit.SECONDS);
		return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class));
	}

}
