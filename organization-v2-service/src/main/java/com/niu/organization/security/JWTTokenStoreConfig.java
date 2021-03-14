package com.niu.organization.security;

import com.niu.organization.config.ServiceConfig;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * JWT 配置类
 *
 * @version 1.0 [2021/03/14 16:47]
 * @author [nza]
 * @createTime [2021/03/14 16:47]
 */
@Configuration
@AllArgsConstructor
@ConditionalOnProperty("jwt.sign.key")
public class JWTTokenStoreConfig {

    private final ServiceConfig serviceConfig;

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 读取令牌中的数据
     * <p>
     * 优先加载
     *
     * @return {@link DefaultTokenServices}
     */
    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    /**
     * 在 JWT 与 OAuth2 服务器之间充当翻译
     *
     * @return {@link JwtAccessTokenConverter}
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(serviceConfig.getJwtSignKey());
        return converter;
    }

    @Bean
    public TokenEnhancer jwtTokenEnhancer() {
        return new JWTTokenEnhancer();
    }
}
