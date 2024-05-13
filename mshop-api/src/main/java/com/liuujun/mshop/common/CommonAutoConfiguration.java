package com.liuujun.mshop.common;

import org.redisson.api.RedissonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouyi
 */
@Configuration
public class CommonAutoConfiguration {

    @Bean
    public LocalCache localCache(RedissonClient redissonClient){
        return new LocalCache(redissonClient);
    }
}
