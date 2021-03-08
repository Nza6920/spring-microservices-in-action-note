package com.niu.licenses.config;

import com.google.common.collect.Lists;
import com.niu.licenses.utils.UserContextInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 22:07]
 * @createTime [2021/03/03 22:07]
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

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
        restTemplate.setInterceptors(Lists.newArrayList(userContextInterceptor));
        return builder.build();
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
}
