package com.baomidou.springwind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;

/**
 * <p>
 * 账户相关操作
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {

	/**
	 * 登录
	 */
	@Login(action = Action.Skip)
	@RequestMapping("/login")
	public String index( Model model ) {
		
		return "/login";
	}
	
	/**
	 * 退出
	 */
	@Login(action = Action.Skip)
	@RequestMapping("/logout")
	public String logout( Model model ) {
		
		return redirectTo("/account/login.html");
	}
	
	/**
	 * 锁定
	 */
	@Login(action = Action.Skip)
	@RequestMapping("/lockscreen")
	public String lockscreen( Model model ) {
		
		return "/lockscreen";
	}
	
}
