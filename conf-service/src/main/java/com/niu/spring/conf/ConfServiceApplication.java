package com.niu.spring.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 配置服务启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 21:17]
 * @createTime [2021/03/01 21:17]
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ConfServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfServiceApplication.class, args);
    }
}
