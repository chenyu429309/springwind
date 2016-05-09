package com.baomidou.springwind.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.springwind.common.aspect.Log;
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

	@Log("菜单查询")
	@Override
	public boolean existRolePermission(Long permissionId) {
		RolePermission rp = new RolePermission();
		rp.setPid(permissionId);
		int rlt = baseMapper.selectCount(rp);
		return rlt >= 1;
	}

}