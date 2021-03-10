package com.niu.spring.zuul.filters;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientConnectionManagerFactory;
import org.springframework.cloud.commons.httpclient.ApacheHttpClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;

/**
 * 自定义 SimpleHostRoutingFilter
 *
 * @version 1.0 [2021/03/10 15:17]
 * @author [nza]
 * @createTime [2021/03/10 15:17]
 */
@Slf4j
public class CustomSimpleHostRoutingFilter extends SimpleHostRoutingFilter {
    public CustomSimpleHostRoutingFilter(ProxyRequestHelper helper, ZuulProperties properties, ApacheHttpClientConnectionManagerFactory connectionManagerFactory, ApacheHttpClientFactory httpClientFactory) {
        super(helper, properties, connectionManagerFactory, httpClientFactory);
    }

    @Override
    public boolean shouldFilter() {

        log.debug("自定义: CustomSimpleHostRoutingFilter");
        // 判断动态路由标记, 若触发了动态路由则不执行此拦截器
        Object specialRouteFlag = RequestContext.getCurrentContext().get(FilterUtil.SPECIAL_ROUTE_FLAG);
        if (specialRouteFlag == null) {
            return super.shouldFilter();
        }
        return !((Boolean) specialRouteFlag) && super.shouldFilter();
    }
}
