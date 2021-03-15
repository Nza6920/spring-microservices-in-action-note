package com.niu.organization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 机构服务启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 16:13]
 * @createTime [2021/03/03 16:13]
 */
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class OrganizationServiceV2Application {
    public static void main(String[] args) {
        SpringApplication.run(OrganizationServiceV2Application.class, args);
    }
}
