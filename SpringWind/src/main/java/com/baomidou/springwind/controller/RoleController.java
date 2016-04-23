package com.baomidou.springwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Role;
import com.baomidou.springwind.service.IRoleService;
import com.baomidou.springwind.service.IUserRoleService;

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
@RequestMapping("/perm/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserRoleService userRoleService;

	@Permission("2002")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/role/list";
	}

	@ResponseBody
	@Permission("2002")
	@RequestMapping("/getRoleList")
	public String getUserList() {
		Page<Role> page = getPage();
		return jsonPage(roleService.selectPage(page, null));
	}

	@ResponseBody
	@Permission("2003")
	@RequestMapping("/delete/{roleId}")
	public String delete(@PathVariable Long roleId) {
		boolean exist = userRoleService.existRoleUser(roleId);
		if (exist) {
			return "false";
		}
		return booleanToString(roleService.deleteById(roleId));
	}
}
