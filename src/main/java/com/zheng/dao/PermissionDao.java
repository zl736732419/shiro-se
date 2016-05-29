package com.zheng.dao;

import com.zheng.domain.Permission;

public interface PermissionDao {

	public Permission createPermission(Permission permission);

	public void deletePermission(Long permissionId);

}
