package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.service.IPermissionService;

/**
 * <p>
 * 首页
 * </p>
 * 
 * @author hubin
 * @Date 2016-04-13
 */
@Controller
public class IndexController extends BaseController {

	@Autowired
	private IPermissionService permissionService;

	/**
	 * 首页
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("currentUser", userService.selectById(getCurrentUserId()));
		model.addAttribute("menuList", permissionService.selectMenuVOByUserId(getCurrentUserId()));
		return "/index";
	}

	/**
	 * 主页
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/home")
	public String home(Model model) {

		return "/home";
	}
}
