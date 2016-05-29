package com.zheng.service;

import java.util.Set;

import com.zheng.domain.User;

public interface UserService {
	public User createUser(User user);

	public void deleteUser(Long userId);

	public User findByUsername(String username);

	public void changePwd(Long userId, String newPwd);
	
	public Set<String> findRoles(String username);

	public Set<String> findPermissions(String username);

	public void correlationRole(Long userId, Long... roleIds);

	public void uncorrelationRole(Long userId, Long... roleIds);
}
