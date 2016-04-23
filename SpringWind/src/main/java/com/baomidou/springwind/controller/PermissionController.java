package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Permission;
import com.baomidou.springwind.service.IPermissionService;

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
@RequestMapping("/perm/permission")
public class PermissionController extends BaseController {

	@Autowired
	private IPermissionService permissionService;

	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/permission/list";
	}

	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/getPermissionList")
	public String getPermissionList() {
		Page<Permission> page = getPage();
		return jsonPage(permissionService.selectPage(page, null));
	}

}
