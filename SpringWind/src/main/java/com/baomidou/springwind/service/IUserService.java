package com.baomidou.springwind.service;

import java.util.List;

import com.baomidou.springwind.entity.User;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IUserService extends IBaseService<User> {

	User selectByLoginName(String loginName);

	boolean deleteByUserId(List<Long> userIds);
}