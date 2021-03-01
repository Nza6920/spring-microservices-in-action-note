package com.niu.spring.conf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 21:17]
 * @createTime [2021/03/01 21:17]
 */
@SpringBootApplication
@EnableConfigServer
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
