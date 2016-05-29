package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class RolePermission {

	private Long roleId;
	private Long permissionId;

	public RolePermission() {
		super();
	}

	public RolePermission(Long roleId, Long permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof RolePermission)) {
			return false;
		}

		RolePermission other = (RolePermission) obj;

		return new EqualsBuilder().append(this.roleId, other.getRoleId())
				.append(this.permissionId, other.getPermissionId()).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("roleId", this.getRoleId())
				.append("permissionId", this.getPermissionId()).build();
	}
}
