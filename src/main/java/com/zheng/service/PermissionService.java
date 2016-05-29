package com.zheng.service;

import com.zheng.domain.Permission;

public interface PermissionService {

	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);

}
