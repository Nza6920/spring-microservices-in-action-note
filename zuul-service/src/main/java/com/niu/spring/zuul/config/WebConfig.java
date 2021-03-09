package com.niu.spring.zuul.config;

import com.niu.spring.zuul.utils.UserContextInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * web配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 16:02]
 * @createTime [2021/03/09 16:02]
 */
@Configuration
public class WebConfig {

    /**
     * 带有 Ribbon 功能的 RestTemplate
     *
     * @return {@link RestTemplate}
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
     * 请求拦截器
     *
     * @return {@link org.springframework.http.client.ClientHttpRequestInterceptor}
     * @author nza
     * @createTime 2021/3/9 16:29
     */
    @Bean
    public ClientHttpRequestInterceptor userContextInterceptor() {
        return new UserContextInterceptor();
    }
}
