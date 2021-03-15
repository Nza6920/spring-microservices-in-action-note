package com.niu.spring.zuul.filters;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.context.RequestContext;
import com.niu.spring.zuul.model.AbTestingRoute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.support.RibbonCommandContext;
import org.springframework.cloud.netflix.ribbon.support.RibbonRequestCustomizer;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonCommandFactory;
import org.springframework.cloud.netflix.zuul.filters.route.RibbonRoutingFilter;

import java.text.MessageFormat;
import java.util.List;

import static com.niu.spring.zuul.filters.FilterUtil.AB_TESTING_ROUTE;

/**
 * 自定义 RibbonRoutingFilter
 *
 * @author [nza]
 * @version 1.0 [2021/03/10 15:36]
 * @createTime [2021/03/10 15:36]
 */
@Slf4j
public class CustomRibbonRoutingFilter extends RibbonRoutingFilter {

    private static final String VERSION_PATTERN = "/{0}/";

    public CustomRibbonRoutingFilter(ProxyRequestHelper helper, RibbonCommandFactory<?> ribbonCommandFactory, List<RibbonRequestCustomizer> requestCustomizers) {
        super(helper, ribbonCommandFactory, requestCustomizers);
    }

    @Override
    public boolean shouldFilter() {

        log.debug("自定义: CustomRibbonRoutingFilter");

        // 判断动态路由标记, 若触发了动态路由则不执行此拦截器 (对SpecialRoutesFilterV2无效)
        Object specialRouteFlag = RequestContext.getCurrentContext().get(FilterUtil.SPECIAL_ROUTE_FLAG);
        if (specialRouteFlag == null) {
            return super.shouldFilter();
        }

        return !((Boolean) specialRouteFlag) && super.shouldFilter();
    }

    @Override
    protected RibbonCommandContext buildCommandContext(RequestContext context) {

        log.debug("自定义: buildCommandContext");

        RibbonCommandContext ribbonCommandContext = super.buildCommandContext(context);

        AbTestingRoute abTestingRoute = (AbTestingRoute) context.get(AB_TESTING_ROUTE);
        if (abTestingRoute != null) {
            String uri = ribbonCommandContext.getUri();

            // 替换版本
            String newUri = StrUtil.replace(uri, MessageFormat.format(VERSION_PATTERN, abTestingRoute.getCurrentVersion()), MessageFormat.format(VERSION_PATTERN, abTestingRoute.getTargetVersion()));
            return new RibbonCommandContext(abTestingRoute.getTargetServiceName(),
                    ribbonCommandContext.getMethod(),
                    newUri,
                    ribbonCommandContext.getRetryable(), ribbonCommandContext.getHeaders(),
                    ribbonCommandContext.getParams(),
                    ribbonCommandContext.getRequestEntity(), this.requestCustomizers,
                    ribbonCommandContext.getContentLength(), ribbonCommandContext.getLoadBalancerKey());
        } else {
            return ribbonCommandContext;
        }
    }
}
