package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户-角色关联实体
 *
 * @author Administrator
 * @data 2016年5月29日 下午3:05:32
 */
public class UserRole {
	private Long userId;
	private Long roleId;

	public UserRole() {
		super();
	}

	public UserRole(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof UserRole)) {
			return false;
		}

		UserRole other = (UserRole) obj;
		return new EqualsBuilder().append(this.getUserId(), other.getUserId())
				.append(this.getRoleId(), other.getRoleId()).build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getRoleId()).append(this.getUserId()).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("userId", this.getUserId()).append("roleId", this.getRoleId()).build();
	}
}
