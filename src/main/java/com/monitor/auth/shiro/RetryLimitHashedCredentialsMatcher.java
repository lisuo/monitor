package com.monitor.auth.shiro;

import com.monitor.common.Constant;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 自定义凭证匹配
 * @Author: lisuo
 * @Date: 2018/10/11
 */
public class RetryLimitHashedCredentialsMatcher extends SimpleCredentialsMatcher {
    private Cache<String, Integer> cache;
    @Autowired
    private CacheManager cacheManager;

    /**
     *
     *  自定义密码错误上限
     *
     */
    private Integer retryMax;

    public Cache<String, Integer> getCache() {
        return cache;
    }

    public void setCache(Cache<String, Integer> cache) {
        this.cache = cache;
    }

    public Integer getRetryMax() {
        return retryMax;
    }

    public void setRetryMax(Integer retryMax) {
        this.retryMax = retryMax;
    }

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        cache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws ExcessiveAttemptsException {
        String username = (String) token.getPrincipal();
        Integer retryCount = cache.get(Constant.AUTH_USER_LOGIN_RETRY_COUNT + username);
        if (retryCount == null) {
            retryCount = Integer.valueOf(1);
            cache.put(Constant.AUTH_USER_LOGIN_RETRY_COUNT + username, retryCount);
        }
        if (retryCount > retryMax) {
            throw new ExcessiveAttemptsException("您已连续错误达" + retryMax + "次！请N分钟后再试");
        }
        if (cache.getClass().getName().contains("RedisCache")) {
            cache.put(Constant.AUTH_USER_LOGIN_RETRY_COUNT + username, ++ retryCount);
        }
        //调用父类的校验方法
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            cache.remove(Constant.AUTH_USER_LOGIN_RETRY_COUNT + username);
        }else {
            throw new IncorrectCredentialsException("密码错误，已错误" + retryCount + "次，最多错误" + getRetryMax() + "次");
        }
        Cache<Object, Object> userInfoCache = cacheManager.getCache(username);
        userInfoCache.put(Constant.AUTH_USER_TOKEN + username, token.getCredentials());
        return true;
    }

}
