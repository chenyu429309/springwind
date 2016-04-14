package com.baomidou.springwind.service;

import com.baomidou.springwind.entity.Permission;
import com.baomidou.springwind.entity.vo.MenuVO;

import java.util.List;

import com.baomidou.framework.service.ISuperService;

/**
 *
 * Permission 表数据服务层接口
 *
 */
public interface IPermissionService extends ISuperService<Permission> {

	List<MenuVO> selectMenuVOByUserId(Long userId);

}