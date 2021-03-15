package com.niu.spring.routes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 路由检索服务启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 13:16]
 * @createTime [2021/03/09 13:16]
 */
@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
public class SpecialRoutesServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpecialRoutesServiceApplication.class, args);
    }
}
