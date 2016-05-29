package com.zheng.credentials;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

/**
 * 限制密码输入错误次数 通过ehcache缓存登录次数
 *
 * @author Administrator
 * @data 2016年5月29日 上午10:30:27
 */
public class RetryLimitHashedCredentialMatcher extends HashedCredentialsMatcher {

	private Ehcache retryPwdCache;

	public RetryLimitHashedCredentialMatcher() {
		CacheManager cacheManager = CacheManager
				.newInstance(CacheManager.class.getClassLoader().getResource("ehcache.xml"));
		retryPwdCache = cacheManager.getEhcache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		String username = (String) token.getPrincipal();
		Element element = retryPwdCache.get(username);
		if (element == null) {
			element = new Element(username, new AtomicInteger(0));
			retryPwdCache.put(element);
		}
		AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }
        
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
        	retryPwdCache.remove(username);
        }
        return matches;
	}

}
