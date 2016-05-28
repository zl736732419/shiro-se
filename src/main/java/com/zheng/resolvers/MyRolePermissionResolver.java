package com.zheng.resolvers;

import java.util.Collection;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import com.google.common.collect.Lists;

/**
 * 这里模拟获取用户角色权限
 *
 * @author Administrator
 * @data 2016年5月23日 下午11:42:54
 */
public class MyRolePermissionResolver implements RolePermissionResolver {

	@Override
	public Collection<Permission> resolvePermissionsInRole(String roleString) {
		if("role1".equals(roleString)) {
			return Lists.newArrayList((Permission)new WildcardPermission("menu:*"));
		}
		return null;
	}

}
