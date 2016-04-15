package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Role;
import com.baomidou.springwind.service.IRoleService;

/**
 * <p>
 * 角色管理相关操作
 * </p>
 *
 *
 * @Author hubin
 * @Date 2016-04-15
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@RequestMapping("/list")
	public String list(Model model) {
		return "/role/list";
	}

	@ResponseBody
	@RequestMapping("/getRoleList")
	public String getUserList() {
		Page<Role> page = getPage();
		return jsonPage(roleService.selectPage(page, null));
	}

}
