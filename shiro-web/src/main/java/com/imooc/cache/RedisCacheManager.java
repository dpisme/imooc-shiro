package com.imooc.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

import javax.annotation.Resource;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/29 0029 下午 12:01
 */
public class RedisCacheManager implements CacheManager {

    @Resource
    private RedisCache redisCache;
    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return redisCache;
    }
}
