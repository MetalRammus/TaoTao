package com.zhiyou100.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {
	/**
	 * 注册
	 * 
	 * @return
	 */
	@RequestMapping("/register")
	public String showRegister() {
		return "register";
	}

	/**
	 * 登录
	 * 回调
	 * @return
	 */
	@RequestMapping("/login")
	public String showLogin(String redirect,Model model) {
		model.addAttribute("redirect",redirect);
		return "login";
	}

}
