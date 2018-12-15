package com.imooc.cache;

import com.imooc.util.JedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @author 1639489689@qq.com
 * @date 2018/7/29 0029 下午 2:10
 */
@Component
public class RedisCache<k,v> implements Cache<k,v> {

    @Resource
    private JedisUtil jedisUtil;

    private final  String CACHE_PREFIX="imooc-cache";

    private  byte[] getkey(k k){
        if(k instanceof String){
            return (CACHE_PREFIX+k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }
    @Override
    public v get(k k) throws CacheException {
        System.out.println("===从redis中获取权限数据,参数k:"+k);
        byte[] value=jedisUtil.get(getkey(k));
        if(value!=null){
            return (v) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public v put(k k, v v) throws CacheException {
        byte[] key=getkey(k);
        byte[] value=SerializationUtils.serialize(v);
        jedisUtil.set(key,value);
        jedisUtil.expire(key,600);
        return v;
    }

    @Override
    public v remove(k k) throws CacheException {
        byte[] key=getkey(k);
        byte[] value=jedisUtil.get(key);
        jedisUtil.del(key);
        if(value!=null){
            return (v) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<k> keys() {
        return null;
    }

    @Override
    public Collection<v> values() {
        return null;
    }
}
