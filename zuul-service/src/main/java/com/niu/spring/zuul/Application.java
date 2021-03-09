package com.niu.spring.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 启动类
 * EnableZuulProxy 使服务成为一个 zuul 服务器
 *
 * @author [nza]
 * @version 1.0 [2021/03/08 11:33]
 * @createTime [2021/03/08 11:33]
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
