package com.niu.licenses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 10:46]
 * @createTime [2021/03/01 10:46]
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        // 参数：
//        // jdbc协议:postgresql子协议://主机地址:数据库端口号/要连接的数据库名
//        String url = "jdbc:postgresql://42.193.117.17:5432/test";
//        // 数据库用户名
//        String user = "postgres";
//        // 数据库密码
//        String password = "nza08157016";
//
//        // 1. 加载Driver类，Driver类对象将自动被注册到DriverManager类中
//        Class.forName("org.postgresql.Driver");
//
//        // 2. 连接数据库，返回连接对象
//        Connection conn = DriverManager.getConnection(url, user, password);
//
//        System.out.println("链接成功");
//    }
}
