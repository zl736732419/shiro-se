package com.zheng.service.impl;

import java.util.Set;

import com.zheng.dao.UserDao;
import com.zheng.dao.impl.UserDaoImpl;
import com.zheng.domain.User;
import com.zheng.service.UserService;
import com.zheng.utils.PasswordHelper;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	private PasswordHelper pwdHelper = new PasswordHelper();

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setPwdHelper(PasswordHelper pwdHelper) {
		this.pwdHelper = pwdHelper;
	}

	@Override
	public User createUser(User user) {
		pwdHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	@Override
	public void deleteUser(Long userId) {
		userDao.deleteUser(userId);
	}

	@Override
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void changePwd(Long userId, String newPwd) {
		User user = userDao.findOne(userId);
		user.setPassword(newPwd);
		pwdHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	@Override
	public Set<String> findRoles(String username) {
		return userDao.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {
		return userDao.findPermissions(username);
	}

	@Override
	public void correlationRole(Long userId, Long... roleIds) {
		userDao.correlationRole(userId, roleIds);
	}

	@Override
	public void uncorrelationRole(Long userId, Long... roleIds) {
		userDao.uncorrelationRole(userId, roleIds);
	}

}
