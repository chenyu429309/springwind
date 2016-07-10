package com.baomidou.springwind.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.annotations.Log;
import com.baomidou.springwind.entity.User;
import com.baomidou.springwind.entity.UserRole;
import com.baomidou.springwind.mapper.UserMapper;
import com.baomidou.springwind.mapper.UserRoleMapper;
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
	private UserRoleMapper userRoleMapper;

	@Log("登录")
	@Override
	public User selectByLoginName(String loginName) {
		User user = new User();
		user.setLoginName(loginName);
		return super.selectOne(user);
	}

	@Log("删除用户")
	@Override
	public boolean deleteByUserId(List<Long> userIds) {
		/*
		 * 删除用户角色，再删除用户
		 */
		for (Long userId : userIds) {
			UserRole ur = new UserRole();
			ur.setUid(userId);
			userRoleMapper.deleteSelective(ur);
		}
		return super.deleteBatchIds(userIds);
	}

	@Log("添加用户")
	@Override
	public boolean insertSelective(User entity) {
		System.err.println(" 覆盖父类方法 ");
		return super.insertSelective(entity);
	}
}