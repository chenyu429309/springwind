package com.baomidou.springwind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.entity.Permission;
import com.baomidou.springwind.service.IPermissionService;
import com.baomidou.springwind.service.IRolePermissionService;

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

	@Autowired
	private IRolePermissionService rolePermissionService;

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

	/**
	 * 根据 ID 删除权限
	 */
	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/delPermission")
	public String delPermission(@RequestParam("ids[]") List<Long> permIds) {
		boolean rlt = false;
		if (permIds != null) {
			rlt = permissionService.deleteBatchIds(permIds);
		}
		return callbackResult(rlt);
	}

	/**
	 * 添加、编辑权限
	 */
	@ResponseBody
	@com.baomidou.kisso.annotation.Permission("2003")
	@RequestMapping("/editPermission")
	public String editPermission( Permission perm ) {
		boolean rlt = false;
		if ( perm != null ) {
			if ( perm.getId() != null ) {
				rlt = permissionService.updateById(perm);
			} else {
				rlt = permissionService.insertSelective(perm);
			}
		}
		return callbackSuccess(rlt);
	}
}
