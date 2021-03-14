package com.niu.licenses.security;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

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
