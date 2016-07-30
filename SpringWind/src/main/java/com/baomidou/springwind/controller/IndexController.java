package com.baomidou.springwind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.springwind.entity.Menu;
import com.baomidou.springwind.service.ISysPermissionService;

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
	private ISysPermissionService sysPermissionService;

	/**
	 * 首页
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/index")
	public String index(Model model) {
		List<Menu> menuList = sysPermissionService.getMenuByPid(0L);
		model.addAttribute("menuList", menuList);
		model.addAttribute("menuTreeJson", toJson(sysPermissionService.getMenuTree(menuList)));
		return "/index";
	}

	/**
	 * 主页
	 */
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/home")
	public String home() {
		return "/home";
	}

	/**
	 * SW 捐赠
	 */
	@Permission(action = Action.Skip)
	@RequestMapping("/donate")
	public String donate() {
		return "/donate";
	}
}
