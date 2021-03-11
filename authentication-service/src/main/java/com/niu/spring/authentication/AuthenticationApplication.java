package com.niu.spring.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

/**
 * 授权服务启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/11 10:08]
 * @createTime [2021/03/11 10:08]
 */
@SpringBootApplication
@EnableEurekaClient
@EnableGlobalAuthentication
public class AuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }
}
