package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 权限实体
 *
 * @author Administrator
 * @data 2016年5月29日 下午3:00:52
 */
public class Permission {

	private Long id;
	private String permission;
	private String description;
	private Boolean available = Boolean.TRUE;

	public Permission() {
		super();
	}

	public Permission(String permission, String description, Boolean available) {
		super();
		this.permission = permission;
		this.description = description;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public String getPermission() {
		return permission;
	}

	public String getDescription() {
		return description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Permission)) {
			return false;
		}

		Permission other = (Permission) obj;

		return new EqualsBuilder().append(this.getPermission(), other.getPermission())
				.append(this.getDescription(), other.getDescription()).append(this.getAvailable(), other.getAvailable())
				.build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getPermission()).append(this.getDescription())
				.append(this.getAvailable()).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("permission", this.getPermission())
				.append("description", this.getDescription()).append("available", this.getAvailable()).build();
	}

}
