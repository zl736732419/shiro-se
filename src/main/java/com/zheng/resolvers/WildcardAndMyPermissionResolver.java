package com.zheng.resolvers;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import com.zheng.permissions.MyPermission;

/**
 * 针对permissionstring解析为权限对象
 * 可以支持自定义MyPermission,和wildcardPermission两种模式
 *
 * @author Administrator
 * @data 2016年5月23日 下午11:39:50
 */
public class WildcardAndMyPermissionResolver implements PermissionResolver {

	@Override
	public Permission resolvePermission(String permissionString) {
		
		if(permissionString.contains("+")) {
			return new MyPermission(permissionString);
		}
		
		return new WildcardPermission(permissionString);
	}

}
