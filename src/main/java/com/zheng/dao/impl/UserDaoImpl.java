package com.zheng.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.zheng.dao.UserDao;
import com.zheng.domain.User;
import com.zheng.utils.JdbcTemplateUtils;

public class UserDaoImpl implements UserDao {

	private JdbcTemplate template = JdbcTemplateUtils.getJdbcTemplate();
	
	@Override
	public User createUser(final User user) {
		final String sql = "insert into sys_users(username, password, salt, locked) values(?,?,?,?)";
		
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement pstat = conn.prepareStatement(sql, new String[] {"id"});
				pstat.setString(1, user.getUsername());
				pstat.setString(2, user.getPassword());
				pstat.setString(3, user.getSalt());
				pstat.setBoolean(4, user.getLocked());
				return pstat;
			}
		}, keyHolder);
		
		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	@Override
	public void updateUser(User user) {
		String sql = "delete from sys_users_roles where user_id=?";
        template.update(sql, user.getId());
		sql = "update sys_users set username=?,password=?,salt=?,locked=? where id=?";
		template.update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(), user.getId());
	}
	
	@Override
	public void deleteUser(Long userId) {
		String sql = "delete from sys_users where id=?";
		template.update(sql, userId);
	}

	@Override
	public User findOne(Long userId) {
		String sql = "select id, username, password, salt, locked from sys_users where user_id=?";
		return template.queryForObject(sql, User.class, userId);
	}

	@Override
	public User findByUsername(String username) {
		String sql = "select id, username, password, salt, locked from sys_users where username = ?";
		List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
		if(list.size() == 0) {
			return null;
		}
		return list.get(0);
	}
	
	@Override
	public Set<String> findRoles(String username) {
		String sql = "SELECT r.role FROM sys_users u INNER JOIN sys_users_roles ur ON u.id=ur.user_id "
				+ " INNER JOIN sys_roles r ON ur.role_id = r.id WHERE u.username=?";
		return new HashSet<String>(template.queryForList(sql, String.class, username));
	}

	@Override
	public Set<String> findPermissions(String username) {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.permission FROM sys_users u INNER JOIN sys_users_roles ur ")
			.append(" ON u.id = ur.user_id ")
			.append(" INNER JOIN sys_roles_permissions rp")
			.append(" ON ur.role_id = rp.role_id")
			.append(" INNER JOIN sys_permissions p")
			.append(" ON rp.permission_id = p.id")
			.append(" WHERE u.username=?");
		return new HashSet<String>(template.queryForList(builder.toString(), String.class, username));
	}

	@Override
	public void correlationRole(Long userId, Long... roleIds) {
		if(userId == null || roleIds.length == 0) {
			return;
		}
		String sql = "insert into sys_users_roles(user_id, role_id) values(?, ?)";
		for(Long roleId : roleIds) {
			if(!exist(userId, roleId)) {
				template.update(sql, userId, roleId);
			}
		}
	}

	/**
	 * 查看当前用户与角色是否已经关联
	 *
	 * @author Administrator
	 * @data 2016年5月29日 下午3:55:04
	 * @param userId
	 * @param roleId
	 * @return
	 */
	private boolean exist(Long userId, Long roleId) {
		String sql = "select count(1) from sys_users_roles where user_id = ? and role_id = ?";
		Integer count = template.queryForObject(sql, Integer.class, userId, roleId);
		return count > 0 ? true : false;
	}

	@Override
	public void uncorrelationRole(Long userId, Long... roleIds) {
		if(userId == null || roleIds.length == 0) {
			return;
		}
		
		String sql = "delete from sys_users_roles where user_id = ? and role_id=?";
		for(Long roleId : roleIds) {
			if(exist(userId, roleId)) {
				template.update(sql, userId, roleId);
			}
		}
		
		
	}

}
