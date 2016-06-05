package com.zheng.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * 限制密码输入错误次数 通过ehcache缓存登录次数
 *
 * @author Administrator
 * @data 2016年5月29日 上午10:30:27
 */
public class RetryLimitHashedCredentialMatcher extends HashedCredentialsMatcher {
	private Cache<String, AtomicInteger> retryPwdCache;

	public RetryLimitHashedCredentialMatcher(CacheManager cacheManager) {
		retryPwdCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		AtomicInteger retryCount = retryPwdCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			retryPwdCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			throw new ExcessiveAttemptsException();
		}

		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			// clear retry count
			retryPwdCache.remove(username);
		}
		return matches;
	}

}
