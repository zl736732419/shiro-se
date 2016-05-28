package com.zheng.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm4 extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addRole("role1");
		info.addRole("role2");
		info.addStringPermission("+user+2+10");
		info.addStringPermission("user2:*");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		if(!"root".equals(username)) {
			throw new UnknownAccountException("用户不存在!");
		}
		
		if(!"root".equals(password)) {
			throw new IncorrectCredentialsException("输入密码错误!");
		}
		
		return new SimpleAuthenticationInfo(username, password, this.getName());
	}

}
