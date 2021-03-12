package com.niu.organization.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务配置类
 *
 * @version 1.0 [2021/03/12 17:21]
 * @author [nza]
 * @createTime [2021/03/12 17:21]
 */
@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置需要认证的请求
        http.authorizeRequests()
                .anyRequest()
                .authenticated();
    }
}
