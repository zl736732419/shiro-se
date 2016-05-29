package com.zheng.service.impl;

import com.zheng.dao.PermissionDao;
import com.zheng.dao.impl.PermissionDaoImpl;
import com.zheng.domain.Permission;
import com.zheng.service.PermissionService;

public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao = new PermissionDaoImpl();
	
	@Override
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
		
	}

}
