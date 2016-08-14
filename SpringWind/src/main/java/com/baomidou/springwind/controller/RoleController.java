package com.baomidou.springwind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserRoleService userRoleService;


	@Permission("2000")
	@RequestMapping("/list")
	public String list( Model model ) {
		return "/role/list";
	}


	@ResponseBody
	@Permission("2000")
	@RequestMapping("/getRoleList")
	public String getUserList() {
		Page<Role> page = getPage();
		return jsonPage(roleService.selectPage(page, null, null, null));
	}

	/**
	 * 根据 ID 删除角色
	 */
	@ResponseBody
	@Permission("2000")
	@RequestMapping("/delRole")
	public String delRole(@RequestParam("ids[]") List<Long> roleIds) {
		boolean rlt = false;
		if (roleIds != null) {
			rlt = roleService.deleteBatchIds(roleIds);
		}
		return callbackResult(rlt);
	}

	/**
	 * 添加、编辑角色
	 */
	@ResponseBody
	@Permission("2000")
	@RequestMapping("/editRole")
	public String editRole( Role role ) {
		boolean rlt = false;
		if ( role != null ) {
			if ( role.getId() != null ) {
				rlt = roleService.updateById(role);
			} else {
				rlt = roleService.insertSelective(role);
			}
		}
		return callbackSuccess(rlt);
	}
}
