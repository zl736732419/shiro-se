package com.zheng.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.zheng.domain.User;

public class PasswordHelper {
	private RandomNumberGenerator generator = new SecureRandomNumberGenerator();
	private String algorithmName = "md5";
	private int hashIterations = 2;

	public RandomNumberGenerator getGenerator() {
		return generator;
	}

	public String getAlgorithmName() {
		return algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setGenerator(RandomNumberGenerator generator) {
		this.generator = generator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void encryptPassword(User user) {
		user.setSalt(generator.nextBytes().toHex());
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();

		user.setPassword(newPassword);
	}

}
