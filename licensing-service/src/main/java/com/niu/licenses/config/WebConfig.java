package com.niu.licenses.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * web 配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 22:07]
 * @createTime [2021/03/03 22:07]
 */
@Configuration
public class WebConfig {

    /**
     * 带有 Ribbon 功能的 RestTemplate
     *
     * @return {@link org.springframework.web.client.RestTemplate}
     * @author nza
     * @createTime 2021/3/3 23:18
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
