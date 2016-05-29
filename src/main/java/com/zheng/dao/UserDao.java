package com.zheng.dao;

import java.util.Set;

import com.zheng.domain.User;

public interface UserDao {
	public User createUser(User user);

	public void updateUser(User user);
	
	public void deleteUser(Long userId);
	
	public User findOne(Long userId);

	public User findByUsername(String username);
	
	public Set<String> findRoles(String username);

	public Set<String> findPermissions(String username);

	public void correlationRole(Long userId, Long... roleIds);

	public void uncorrelationRole(Long userId, Long... roleIds);
}
