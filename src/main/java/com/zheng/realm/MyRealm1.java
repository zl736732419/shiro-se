package com.zheng.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm1 implements Realm {

	@Override
	public String getName() {
		return "myRealm1";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return (token instanceof UsernamePasswordToken);
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		if(!"root".equals(username)) {
			throw new UnknownAccountException("未知用户 " + username + "!");
		}else if(!"root".equals(password)) {
			throw new IncorrectCredentialsException("用户密码错误!");
		}
		
		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
