package com.baomidou.springwind.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.kisso.SSOAuthorization;
import com.baomidou.kisso.Token;
import com.baomidou.springwind.common.CacheableValues;
import com.baomidou.springwind.entity.Menu;
import com.baomidou.springwind.entity.SysPermission;
import com.baomidou.springwind.entity.vo.MenuTreeVO;
import com.baomidou.springwind.entity.vo.MenuVO;
import com.baomidou.springwind.entity.vo.MenuZtreeVO;
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

	@Override
	public List<Menu> getMenuByPid(Long pid) {
		return baseMapper.selectMenuByPid(pid);
	}

	@Override
	public List<MenuTreeVO> getMenuTree(List<Menu> menuList) {
		List<MenuTreeVO> mtvl = new ArrayList<MenuTreeVO>();
		for (Menu menu : menuList) {
			/* 一级菜单 */
			MenuTreeVO mtv = new MenuTreeVO();
			mtv.setId(menu.getId());
			mtv.setHomePage(menu.getHref());

			/* 二级菜单 */
			List<MenuVO> mvl = new ArrayList<MenuVO>();
			List<Menu> ml = baseMapper.selectMenuByPid(menu.getTid());
			for (Menu m1 : ml) {
				MenuVO mv = new MenuVO();
				mv.setText(m1.getText());

				/* 三级菜单 */
				mv.setItems(baseMapper.selectMenuByPid(m1.getTid()));
				mvl.add(mv);
			}
			mtv.setMenu(mvl);
			mtvl.add(mtv);
		}
		return mtvl;
	}

	@CacheEvict(value = CacheableValues.PERMISSION)
	@Override
	public List<MenuZtreeVO> getMenuZtree() {
		/*
		 * 从根节点开始查询，一级菜单
		 */
		List<MenuZtreeVO> menuTreeList = new ArrayList<MenuZtreeVO>();
		menuTreeList.add(new MenuZtreeVO(0L, -1L, "菜单 Root", true));/* 设置菜单根节点 */
		List<MenuZtreeVO> mtvl = baseMapper.selectMenuTreeByPid(0L);
		for (MenuZtreeVO m : mtvl) {
			m.setOpen(true);
			menuTreeList.add(m);

			/* 二级菜单 */
			List<MenuZtreeVO> mt1 = baseMapper.selectMenuTreeByPid(m.getId());
			for (MenuZtreeVO m1 : mt1) {
				m1.setOpen(true);
				menuTreeList.add(m1);

				/* 三级菜单 */
				List<MenuZtreeVO> mt2 = baseMapper.selectMenuTreeByPid(m1.getId());
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
					if (null != p && permission.equals(p.getCode())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Cacheable(value = CacheableValues.PERMISSION, key = "#userId")
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