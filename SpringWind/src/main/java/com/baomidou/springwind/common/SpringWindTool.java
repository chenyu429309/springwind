package com.baomidou.springwind.common;

import org.apache.velocity.tools.config.DefaultKey;

import com.baomidou.framework.spring.SpringContextHolder;
import com.baomidou.framework.spring.SpringHelper;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.Token;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.service.ISysPermissionService;
import com.baomidou.springwind.service.IUserService;

/**
 * <p>
 * SpringWind 标签工具类
 * </p>
 * 
 * @author hubin
 * @Date 2016-07-08
 */
@DefaultKey("springWind")
public class SpringWindTool {

	/**
	 * 当前登录用户信息
	 */
	public User currentUser() {
		Token token = SSOHelper.attrToken(SpringHelper.getHttpServletRequest());
		if (token == null) {
			return null;
		}
		IUserService userService = SpringHelper.getBean(IUserService.class);
		return userService.selectById(token.getId());
	}

	/**
	 * 按钮级别权限判断
	 */
	public boolean isActionable(String permission) {
		Token token = SSOHelper.attrToken(SpringHelper.getHttpServletRequest());
		if (token == null) {
			return false;
		}
		// 数据库判断按钮权限是否合法，生产环境此处建议加缓存判断逻辑。
		ISysPermissionService psi = SpringContextHolder.getBean(ISysPermissionService.class);
		return psi.isActionable(token, permission);
	}
}
