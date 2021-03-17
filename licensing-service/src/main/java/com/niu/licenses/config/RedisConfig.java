package com.niu.licenses.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Redis 配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/17 14:53]
 * @createTime [2021/03/17 14:53]
 */
@Component
@Data
public class RedisConfig {

    @Value("${spring.redis.host:#{'127.0.0.1'}}")
    private String hostName;

    @Value("${spring.redis.port:#{6379}}")
    private int port;

    @Value("${spring.redis.timeout:#{3000}}")
    private int timeout;

    @Value("${spring.redis.lettuce.pool.max-idle:#{16}}")
    private int maxIdle;

    @Value("${spring.redis.lettuce.pool.min-idle:#{1}}")
    private int minIdle;

    @Value("${spring.redis.lettuce.pool.max-wait:#{16}}")
    private long maxWaitMillis;

    @Value("${spring.redis.lettuce.pool.max-active:#{16}}")
    private int maxActive;

    @Value("${spring.redis.database:#{0}}")
    private int dbIndex;
}
