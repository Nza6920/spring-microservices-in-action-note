package com.niu.spring.authentication.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.ClientsConfiguredCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * <功能简述>
 *
 * @author [nza]
 * @version 1.0 [2021/03/11 17:32]
 * @createTime [2021/03/11 17:32]
 */
@EnableWebSecurity
@EnableAuthorizationServer
public class OAuth2LoginConfig extends WebSecurityConfigurerAdapter {


}
