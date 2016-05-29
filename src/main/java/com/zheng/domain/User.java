package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户实体
 *
 * @author Administrator
 * @data 2016年5月29日 下午2:46:43
 */
public class User {
	private Long id;
	private String username;
	private String password;
	private String salt;
	private Boolean locked = Boolean.FALSE;

	public User() {
		super();
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User)) {
			return false;
		}

		User other = (User) obj;

		return new EqualsBuilder().append(this.username, other.getUsername()).append(this.password, other.getPassword())
				.append(this.salt, other.getSalt()).append(this.getLocked(), other.getLocked()).build();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.getUsername()).append(this.getPassword()).append(this.getSalt())
				.append(this.getLocked()).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("username", this.getUsername()).append("password", this.getPassword())
				.append("salt", this.getSalt()).append("locked", this.getLocked()).build();

	}

	public String getCredentialsSalt() {
		return username + salt;
	}
}
