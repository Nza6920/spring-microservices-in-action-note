package com.niu.spring.authentication.controller;

import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 授权控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/11 11:15]
 * @createTime [2021/03/11 11:15]
 */
@RestController
@RequestMapping("authentication")
@EnableGlobalAuthentication
public class AuthenticationController {

}
