package com.baomidou.springwind.service;

import java.util.List;

import com.baomidou.framework.service.ISuperService;
import com.baomidou.springwind.entity.Permission;
import com.baomidou.springwind.entity.vo.MenuVO;

/**
 *
 * Permission 表数据服务层接口
 *
 */
public interface IPermissionService extends ISuperService<Permission> {

	List<MenuVO> selectMenuVOByUserId(Long userId);

	List<Permission> selectAllByUserId(Long userId);

}