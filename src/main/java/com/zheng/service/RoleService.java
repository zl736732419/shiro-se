package com.zheng.service;

import com.zheng.domain.Role;

public interface RoleService {

	public Role createRole(Role role);
	public void deleteRole(Long roleId);
	
	/**
	 * 关联角色与权限
	 *
	 * @author Administrator
	 * @data 2016年5月29日 下午3:16:00
	 * @param roleId
	 * @param permissionIds
	 */
	public void correlationPermission(Long roleId, Long...permissionIds);
	
	public void uncorrelationPermission(Long roleId, Long...permissionIds);
	
}
