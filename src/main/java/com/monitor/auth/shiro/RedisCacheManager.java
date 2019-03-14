package com.monitor.auth.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description: shiro redis cache管理类
 * @Author: lisuo
 * @Date: 2018/10/11:14:22
 */
@Component
public class RedisCacheManager implements CacheManager {

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<>();
    @Autowired
    private RedisCache redisCache;

    @Override
    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        Cache<K, V> cache = caches.get(name);
        if (Objects.isNull(cache)){
            createCache(name);
        }
        return redisCache;
    }


    public Cache createCache(String name) throws CacheException{
        this.redisCache = new RedisCache();
        caches.putIfAbsent(name, redisCache);
        return new RedisCache();
    }

}
