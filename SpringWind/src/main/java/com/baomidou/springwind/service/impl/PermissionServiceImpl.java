package com.baomidou.springwind.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.springwind.entity.Permission;
import com.baomidou.springwind.entity.vo.MenuVO;
import com.baomidou.springwind.mapper.PermissionMapper;
import com.baomidou.springwind.service.IPermissionService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * Permission 表数据服务层接口实现类
 *
 */
@Service
public class PermissionServiceImpl extends BaseServiceImpl<PermissionMapper, Permission> implements IPermissionService {

	@Override
	public List<MenuVO> selectMenuVOByUserId(Long userId) {
		List<MenuVO> perList = autoMapper.selectMenuByUserId(userId, 0L);
		if(perList == null || perList.isEmpty()){
			return null;
		}
		List<MenuVO> mvList = new ArrayList<MenuVO>();
		for (MenuVO mv : perList) {
			mv.setMvList(autoMapper.selectMenuByUserId(userId, mv.getId()));
			mvList.add(mv);
		}
		return mvList;
	}


}