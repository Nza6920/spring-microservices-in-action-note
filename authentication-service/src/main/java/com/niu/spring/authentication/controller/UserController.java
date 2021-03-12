package com.niu.spring.authentication.controller;

import com.google.common.collect.Maps;
import com.niu.spring.authentication.pojo.ServerResponse;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 用户控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/11 11:16]
 * @createTime [2021/03/11 11:16]
 */
@RestController
@RequestMapping("users")
public class UserController {

    /**
     * 当前登录用户
     *
     * @param authentication 当前用户凭证
     * @author nza
     * @createTime 2021/3/12 17:08
     * @return    {@link com.niu.spring.authentication.pojo.ServerResponse<java.util.Map<java.lang.String,java.lang.Object>>}
     */
    @GetMapping("me")
    public ServerResponse<Map<String, Object>> findUser(OAuth2Authentication authentication) {
        Map<String, Object> userInfo = Maps.newHashMap();
        userInfo.put("user", authentication.getUserAuthentication().getPrincipal());
        userInfo.put("authorities", AuthorityUtils.authorityListToSet(authentication.getUserAuthentication().getAuthorities()));
        return ServerResponse.createBySuccess(userInfo);
    }
}
