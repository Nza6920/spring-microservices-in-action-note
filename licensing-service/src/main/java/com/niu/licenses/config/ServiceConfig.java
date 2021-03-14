package com.niu.licenses.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 服务配置
 *
 * @author [nza]
 * @version 1.0 [2021/03/02 21:45]
 * @createTime [2021/03/02 21:45]
 */
@Component
@Data
public class ServiceConfig {

    @Value("${example.property}")
    private String exampleProperty;

    @Value("${jwt.sign.key}")
    private String jwtSignKey;
}
