package com.baomidou.springwind.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.Token;
import com.baomidou.springwind.entity.SysPermission;
import com.baomidou.springwind.entity.vo.MenuTreeVO;
import com.baomidou.springwind.entity.vo.MenuVO;
import com.baomidou.springwind.mapper.SysPermissionMapper;
import com.baomidou.springwind.service.ISysPermissionService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 * <p>
 * Permission 表数据服务层接口实现类
 * </p>
 * <p>
 * 实现权限接口 SSOAuthorization
 * </p>
 *
 */
@Service
public class SysPermissionServiceImpl extends BaseServiceImpl<SysPermissionMapper, SysPermission>
		implements ISysPermissionService, SSOAuthorization {

	@Cacheable(value = "permissionCache", key = "#userId")
	@Override
	public List<MenuVO> selectMenuVOByUserId(Long userId) {
		List<MenuVO> perList = baseMapper.selectMenuByUserId(userId, 0L);
		if (perList == null || perList.isEmpty()) {
			return null;
		}
		List<MenuVO> mvList = new ArrayList<MenuVO>();
		for (MenuVO mv : perList) {
			mv.setMvList(baseMapper.selectMenuByUserId(userId, mv.getId()));
			mvList.add(mv);
		}
		return mvList;
	}

	@Override
	public List<MenuTreeVO> getMenuTree() {
		/*
		 * 从根节点开始查询，一级菜单
		 */
		List<MenuTreeVO> menuTreeList = new ArrayList<MenuTreeVO>();
		menuTreeList.add(new MenuTreeVO(0L, -1L, "菜单", true));/* 设置菜单根节点 */
		List<MenuTreeVO> mtvl = baseMapper.selectByPid(0L);
		for (MenuTreeVO m : mtvl) {
			m.setOpen(true);
			menuTreeList.add(m);

			/* 二级菜单 */
			List<MenuTreeVO> mt1 = baseMapper.selectByPid(m.getId());
			for (MenuTreeVO m1 : mt1) {
				m1.setOpen(true);
				menuTreeList.add(m1);

				/* 三级菜单 */
				List<MenuTreeVO> mt2 = baseMapper.selectByPid(m1.getId());
				if (!mt2.isEmpty()) {
					menuTreeList.addAll(mt2);
				}
			}
		}
		return menuTreeList;
	}

	@Override
	public boolean isPermitted(Token token, String permission) {
		/**
		 * 
		 * 菜单级别、权限验证，生产环境建议加上缓存处理。
		 * 
		 */
		if (StringUtils.isNotBlank(permission)) {
			List<SysPermission> pl = this.selectAllByUserId(token.getId());
			if (pl != null) {
				for (SysPermission p : pl) {
					if (null != p && permission.equals(p.getPermCode())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Cacheable(value = "permissionCache", key = "#userId")
	@Override
	public List<SysPermission> selectAllByUserId(Long userId) {
		return baseMapper.selectAllByUserId(userId);
	}

	@Override
	public boolean isActionable(Token token, String permission) {
		/**
		 * 
		 * 按钮级别、权限验证，生产环境建议加上缓存处理。 <br>
		 * 演示 admin 返回 true
		 * 
		 */
		System.err.println(" isActionable =" + permission);
		if (token.getId() == 1L) {
			return true;
		}
		return false;
	}

}