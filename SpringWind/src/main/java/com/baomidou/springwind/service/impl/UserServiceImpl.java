package com.baomidou.springwind.service.impl;

import com.baomidou.springwind.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.service.IUserService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private IRoleService  roleService;

	@Override
	public User selectByLoginName(String loginName) {
		User user = new User();
		user.setLoginName(loginName);
		return super.selectOne(user);
	}

	@Override
	public void deleteUser(Long userId) {
		//删除用户角色，再删除用户
		roleService.deleteByUserId(userId);
		super.deleteById(userId);
	}

}