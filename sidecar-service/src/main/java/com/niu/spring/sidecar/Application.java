package com.niu.spring.sidecar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

/**
 * 启动类
 * EnableSidecar 开启 Sidecar 服务
 * @author [nza]
 * @version 1.0 [2021/03/08 16:11]
 * @createTime [2021/03/08 16:11]
 */
@SpringBootApplication
@EnableSidecar
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
