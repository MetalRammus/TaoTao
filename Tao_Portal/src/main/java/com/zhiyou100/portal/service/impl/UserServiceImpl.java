package com.zhiyou100.portal.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhiyou100.model.TbUser;
import com.zhiyou100.portal.service.UserService;
import com.zhiyou100.utils.HttpClientUtil;
import com.zhiyou100.utils.TaotaoResult;

@Service
public class UserServiceImpl implements UserService {

	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL;
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN;
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN;
	
	@Override
	public TbUser getUserByToken(String token) {
		try {
			//通过令牌查找用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			TaotaoResult result=TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus()==200) {
				TbUser user=(TbUser) result.getData();
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
