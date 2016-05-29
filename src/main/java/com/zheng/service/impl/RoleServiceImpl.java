package com.zheng.service.impl;

import com.zheng.dao.RoleDao;
import com.zheng.dao.impl.RoleDaoImpl;
import com.zheng.domain.Role;
import com.zheng.service.RoleService;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao = new RoleDaoImpl();
	
	@Override
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	@Override
	public void correlationPermission(Long roleId, Long... permissionIds) {
		roleDao.correlationPermission(roleId, permissionIds);
	}

	@Override
	public void uncorrelationPermission(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermission(roleId, permissionIds);
	}

}
