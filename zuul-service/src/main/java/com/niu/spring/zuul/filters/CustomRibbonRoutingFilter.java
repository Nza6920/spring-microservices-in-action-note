package com.niu.spring.zuul.filters;

import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;

import java.util.List;

/**
 * 自定义 RibbonRoutingFilter
 *
 * @author [nza]
 * @version 1.0 [2021/03/10 15:36]
 * @createTime [2021/03/10 15:36]
 */
@Slf4j
public class CustomRibbonRoutingFilter extends RibbonRoutingFilter {
    public CustomRibbonRoutingFilter(ProxyRequestHelper helper, RibbonCommandFactory<?> ribbonCommandFactory, List<RibbonRequestCustomizer> requestCustomizers) {
        super(helper, ribbonCommandFactory, requestCustomizers);
    }

    @Override
    public boolean shouldFilter() {

        log.debug("自定义: CustomRibbonRoutingFilter");

        // 判断动态路由标记, 若触发了动态路由则不执行此拦截器
        Object specialRouteFlag = RequestContext.getCurrentContext().get(FilterUtil.SPECIAL_ROUTE_FLAG);
        if (specialRouteFlag == null) {
            return super.shouldFilter();
        }

        return !((Boolean) specialRouteFlag) && super.shouldFilter();
    }
}
