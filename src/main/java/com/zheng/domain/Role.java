package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 角色实体
 *
 * @author Administrator
 * @data 2016年5月29日 下午2:54:19
 */
public class Role {
	private Long id;
	private String role; // 程序中判断使用，如admin
	private String description;
	private Boolean available = Boolean.TRUE;

	public Role() {
		super();
	}

	public Role(String role, String description, Boolean available) {
		super();
		this.role = role;
		this.description = description;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public String getRole() {
		return role;
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

	public void setRole(String role) {
		this.role = role;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getId()).append(this.getRole()).append(this.getDescription())
				.append(this.getAvailable()).build();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Role)) {
			return false;
		}

		Role other = (Role) obj;

		return new EqualsBuilder().append(this.getRole(), other.getRole())
				.append(this.getDescription(), other.getDescription()).append(this.getAvailable(), other.getAvailable())
				.build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("role", this.getRole()).append("description", this.getDescription())
				.append("available", this.getAvailable()).build();
	}

}
