package com.niu.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 * <p>
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 10:46]
 * @RefreshScope 刷新配置文件
 * 访问：http://ip:port/refresh
 * <p/>
 * @createTime [2021/03/01 10:46]
 */
@SpringBootApplication
//@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
