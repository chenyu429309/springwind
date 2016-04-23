package com.baomidou.springwind.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.springwind.entity.RolePermission;
import com.baomidou.springwind.mapper.RolePermissionMapper;
import com.baomidou.springwind.service.IRolePermissionService;
import com.baomidou.springwind.service.support.BaseServiceImpl;

/**
 *
 * RolePermission 表数据服务层接口实现类
 *
 */
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission>
		implements IRolePermissionService {

	@Override
	public boolean existRolePermission(Long permissionId) {
		RolePermission rp = new RolePermission();
		rp.setPid(permissionId);
		List<RolePermission> rpList = autoMapper.selectList(new EntityWrapper<RolePermission>(rp, null));
		if (rpList != null && !rpList.isEmpty()) {
			return true;
		}
		return false;
	}

}