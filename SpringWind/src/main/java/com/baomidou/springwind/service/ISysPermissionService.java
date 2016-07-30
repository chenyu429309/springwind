package com.baomidou.springwind.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.kisso.Token;
import com.baomidou.springwind.entity.Menu;
import com.baomidou.springwind.entity.SysPermission;
import com.baomidou.springwind.entity.vo.MenuTreeVO;
import com.baomidou.springwind.entity.vo.MenuZtreeVO;

/**
 *
 * Permission 表数据服务层接口
 *
 */
public interface ISysPermissionService extends ISuperService<SysPermission> {

	List<SysPermission> selectAllByUserId(Long userId);

	List<Menu> getMenuByPid(Long pid);

	/**
	 * <p>
	 * 获取菜单树列表
	 * </p>
	 */
	List<MenuTreeVO> getMenuTree(List<Menu> menuList);

	/**
	 * <p>
	 * 获取 zTree 菜单树列表
	 * </p>
	 */
	List<MenuZtreeVO> getMenuZtree();

	/**
	 * <p>
	 * 是否为可操作的权限
	 * </p>
	 * 
	 * @param token
	 *            SSO 票据顶级父类
	 * @param permission
	 *            操作权限编码
	 * @return
	 */
	boolean isActionable(Token token, String permission);
}