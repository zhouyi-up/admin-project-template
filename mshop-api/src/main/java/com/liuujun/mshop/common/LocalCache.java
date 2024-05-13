package com.liuujun.mshop.common;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouyi
 */
public class LocalCache {

    private final RedissonClient redissonClient;

    public LocalCache(RedissonClient redissonClient){
        this.redissonClient = redissonClient;
    }

    public Object get(String key){
       return redissonClient.getBucket(key).get();
    }

    public void put(String key, Object value){
        this.redissonClient.getBucket(key).set(value, 30, TimeUnit.MINUTES);
    }

    public void delete(String key){
        this.redissonClient.getBucket(key).delete();
    }
}
