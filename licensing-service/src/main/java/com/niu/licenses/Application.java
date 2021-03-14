package com.niu.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * 启动类
 * <p>
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 10:46]
 * @RefreshScope 刷新配置文件
 * 访问：http://ip:port/refresh
 * EnableCircuitBreaker 使用 hystrix
 * EnableDiscoveryClient 使用 DiscoveryClient {@link com.niu.licenses.client.OrganizationDiscoveryClient}
 * <p/>
 * @createTime [2021/03/01 10:46]
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableEurekaClient
@EnableCircuitBreaker
@EnableResourceServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
