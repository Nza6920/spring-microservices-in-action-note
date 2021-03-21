package com.niu.licenses.config;

import com.niu.licenses.utils.UserContextInterceptor;
import lombok.AllArgsConstructor;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.List;

/**
 * web 配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 22:07]
 * @createTime [2021/03/03 22:07]
 */
@Configuration
@AllArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final RedisConfig redisConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor())
                .addPathPatterns("/v1/organizations/**");
    }

    /**
     * 带有 Ribbon 功能的 RestTemplate
     *
     * @return {@link org.springframework.web.client.RestTemplate}
     * @author nza
     * @createTime 2021/3/3 23:18
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ClientHttpRequestInterceptor userContextInterceptor) {
        RestTemplate restTemplate = builder.build();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(userContextInterceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /**
     * 用户上下文拦截器
     *
     * @return {@link com.niu.licenses.utils.UserContextInterceptor}
     * @author nza
     * @createTime 2021/3/7 12:37
     */
    @Bean
    public ClientHttpRequestInterceptor userContextInterceptor() {
        return new UserContextInterceptor();
    }


    /**
     * 配置 lettuce 连接工厂
     *
     * @return {@link org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory}
     * @author nza
     * @createTime 2021/3/17 14:57
     */
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {

        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration(redisConfig.getHostName(), redisConfig.getPort());

        // 设置选用的数据库号码
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(redisConfig.getDbIndex());

        // 连接池配置
        GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(redisConfig.getMaxIdle());
        poolConfig.setMinIdle(redisConfig.getMinIdle());
        poolConfig.setMaxTotal(redisConfig.getMaxActive());
        poolConfig.setMaxWaitMillis(redisConfig.getMaxWaitMillis());

        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder
                = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(redisConfig.getTimeout()));

        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = builder.build();

        builder.poolConfig(poolConfig);

        // 根据配置和客户端配置创建连接
        return new LettuceConnectionFactory(redisConfiguration, lettucePoolingClientConfiguration);
    }

    /**
     * 配置 redisTemplate
     *
     * @author nza
     * @createTime 2021/3/17 14:58
     * @return    {@link org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>}
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
