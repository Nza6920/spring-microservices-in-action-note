package com.niu.spring.zuul.config;

import com.niu.spring.zuul.filters.CustomRibbonRoutingFilter;
import com.niu.spring.zuul.filters.CustomSimpleHostRoutingFilter;
import com.niu.spring.zuul.utils.UserContextInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * web配置类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 16:02]
 * @createTime [2021/03/09 16:02]
 */
@Configuration
@AllArgsConstructor
public class WebConfig {

    private final List<RibbonRequestCustomizer> requestCustomizers = Collections.emptyList();

    /**
     * 带有 Ribbon 功能的 RestTemplate
     *
     * @return {@link RestTemplate}
     * @author nza
     * @createTime 2021/3/3 23:18
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, ClientHttpRequestInterceptor userContextInterceptor) {
        RestTemplate restTemplate = builder.build();
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        interceptors.add(userContextInterceptor);
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    /**
     * 请求拦截器
     *
     * @return {@link ClientHttpRequestInterceptor}
     * @author nza
     * @createTime 2021/3/9 16:29
     */
    @Bean
    public ClientHttpRequestInterceptor userContextInterceptor() {
        return new UserContextInterceptor();
    }

    /**
     * 自定义 SimpleHostRoutingFilter
     *
     * @return {@link CustomSimpleHostRoutingFilter}
     * @author nza
     */
//    @Bean
    public SimpleHostRoutingFilter customSimpleHostRoutingFilter(ProxyRequestHelper helper,
                                                                 ZuulProperties zuulProperties,
                                                                 ApacheHttpClientConnectionManagerFactory connectionManagerFactory,
                                                                 ApacheHttpClientFactory httpClientFactory) {
        return new CustomSimpleHostRoutingFilter(helper, zuulProperties,
                connectionManagerFactory, httpClientFactory);
    }

    /**
     * 自定义 RibbonRoutingFilter
     *
     * @return {@link CustomRibbonRoutingFilter}
     * @author nza
     */
    @Bean
    public RibbonRoutingFilter ribbonRoutingFilter(ProxyRequestHelper helper, RibbonCommandFactory<?> ribbonCommandFactory) {
        return new CustomRibbonRoutingFilter(helper, ribbonCommandFactory, this.requestCustomizers);
    }
}
