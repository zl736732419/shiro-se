package com.zheng.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.zheng.domain.User;
import com.zheng.service.UserService;
import com.zheng.service.impl.UserServiceImpl;

public class UserRealm extends AuthorizingRealm {
	
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = (String) principals.getPrimaryPrincipal();
		//根据用户获取相印的角色以及权限
		Set<String> roles = userService.findRoles(username);
		info.addRoles(roles);
		
		Set<String> permissions = userService.findPermissions(username);
		info.setStringPermissions(permissions);
		
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new UnknownAccountException("当前用户" + username + "不存在!");
		}
		
		if(Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException("登录失败,当前用户已禁用!");
		}
		
		//否则交由下一步判断用户名和密码是否正确，这里不做判断
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				username,
				user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()),
				getName()
		);
		return info;
	}
}
