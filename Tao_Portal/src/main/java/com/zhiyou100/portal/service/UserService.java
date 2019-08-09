package com.zhiyou100.portal.service;

import com.zhiyou100.model.TbUser;

public interface UserService {

	TbUser getUserByToken(String token);
}
