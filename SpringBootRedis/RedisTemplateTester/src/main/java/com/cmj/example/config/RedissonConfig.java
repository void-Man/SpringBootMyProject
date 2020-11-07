package com.cmj.example.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author mengjie_chen
 * @description
 * @date 2020/11/7
 */
@Configuration
public class RedissonConfig {

    @Bean(name = "redisson")
    public Redisson redisson(RedisConnectionFactory redisConnectionFactory) {
        Config config = new Config();
        return null;
    }
}
