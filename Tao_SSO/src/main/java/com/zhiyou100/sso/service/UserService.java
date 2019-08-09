package com.zhiyou100.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhiyou100.model.TbUser;
import com.zhiyou100.utils.TaotaoResult;

/**
 * 
 * @author 19229
 *
 */
public interface UserService {
	/**
	 * 校验数据
	 * 
	 * @param content
	 * @param type
	 * @return
	 */
	TaotaoResult checkData(String content, Integer type);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	TaotaoResult createUser(TbUser user);

	/**
	 * 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	TaotaoResult userLogin(String username, String password,HttpServletRequest request, HttpServletResponse response);
	
	/**
	 * 通过令牌查找用户信息
	 * 
	 * @param token
	 * @return
	 */
	TaotaoResult getUserByToken(String token);
}
