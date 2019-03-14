package com.monitor.auth.shiro;


import com.monitor.common.utils.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 权限redis缓存
 * @Author: lisuo
 * @Date: 2018/10/11
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {
    private long expireTime = 1800;

    @Autowired
    private static RedisTemplate redisTemplate;

    static {
        redisTemplate = RedisUtil.redisTemplate;
    }

    /**
     * 通过构造方法注入该对象
     */
    public RedisCache() {
        super();
        redisTemplate = RedisUtil.redisTemplate;
    }


    /**
     * 通过key来获取对应的缓存对象	 * 通过源码我们可以发现，shiro需要的key的类型为Object，V的类型为AuthorizationInfo对象
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public Object get(Object key) throws CacheException {
        return RedisUtil.redisTemplate.opsForValue().get(key);
    }

    /**
     * 将权限信息加入缓存中
     * @param key
     * @param value
     * @return
     * @throws CacheException
     */
    @Override
    public V put(K key, V value) throws CacheException {
        redisTemplate.opsForValue().set(key, value, this.expireTime, TimeUnit.SECONDS);
        return value;
    }

    /**
     * 将权限信息从缓存中删除
     * @param key
     * @return
     * @throws CacheException
     */
    @Override
    public Object remove(Object key) throws CacheException {
        Object v = redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().getOperations().delete(key);
        return v;
    }


    @Override
    public void clear() throws CacheException {


    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

}
