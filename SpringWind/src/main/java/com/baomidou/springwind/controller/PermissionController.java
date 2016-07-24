package com.baomidou.springwind.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.springwind.common.enums.OperateState;
import com.baomidou.springwind.common.enums.SYsPermissionType;
import com.baomidou.springwind.entity.SysPermission;
import com.baomidou.springwind.service.IRolePermissionService;
import com.baomidou.springwind.service.ISysPermissionService;

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
@RequestMapping("/permission")
public class PermissionController extends BaseController {

	@Autowired
	private ISysPermissionService sysPermissionService;

	@Autowired
	private IRolePermissionService rolePermissionService;

	/**
	 * 菜单管理
	 */
	@Permission("2000")
	@RequestMapping("/menu")
	public String menu(Model model) {
		model.addAttribute("menuTreeJson", toJson(sysPermissionService.getMenuTree()));
		return "/permission/menu";
	}

	/**
	 * 添加菜单
	 */
	@Permission("2000")
	@RequestMapping("/menu/add")
	public String addMenu(Model model) {
		model.addAttribute("type", SYsPermissionType.MENU.key());
		return "/permission/edit";
	}

	@Permission("2000")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/permission/list";
	}

	@ResponseBody
	@Permission("2000")
	@RequestMapping("/getPermissionList")
	public String getPermissionList() {
		Page<SysPermission> page = getPage();
		return jsonPage(sysPermissionService.selectPage(page, null));
	}

	/**
	 * 根据 ID 删除权限
	 */
	@ResponseBody
	@Permission("2000")
	@RequestMapping("/delPermission")
	public String delPermission(@RequestParam("ids[]") List<Long> permIds) {
		boolean rlt = false;
		if (permIds != null) {
			rlt = sysPermissionService.deleteBatchIds(permIds);
		}
		return callbackResult(rlt);
	}

	/**
	 * 添加、编辑权限
	 */
	@Permission("2000")
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(SysPermission perm) {
		boolean rlt = false;
		if (perm != null) {
			perm.setState(OperateState.NORMAL.key());
			if (perm.getId() != null) {
				rlt = sysPermissionService.updateById(perm);
			} else {
				rlt = sysPermissionService.insertSelective(perm);
			}
		}
		return callbackSuccess(rlt);
	}

}
