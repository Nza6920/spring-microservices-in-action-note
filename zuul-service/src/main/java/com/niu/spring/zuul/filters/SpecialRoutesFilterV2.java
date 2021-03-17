package com.niu.spring.zuul.filters;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.niu.spring.zuul.model.AbTestingRoute;
import com.niu.spring.zuul.pojo.ServerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static com.niu.spring.zuul.filters.FilterUtil.AB_TESTING_ROUTE;

/**
 * 动态路由过滤器
 * 参考 {@link SimpleHostRoutingFilter}
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 16:00]
 * @createTime [2021/03/09 16:00]
 */
@Component
@Slf4j
@AllArgsConstructor
public class SpecialRoutesFilterV2 extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private final FilterUtil filterUtil;

    private final RestTemplate restTemplate;

    /**
     * 动态路由过滤器
     *
     * @return {@link String} 过滤器类型
     */
    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
    }

    /**
     * 定义不同类型过滤器的执行顺序
     *
     * @return {@link int}
     */
    @Override
    public int filterOrder() {
        return FILTER_ORDER;
    }

    /**
     * 判断该过滤器是否要执行
     *
     * @return {@link boolean}
     */
    @Override
    public boolean shouldFilter() {
        return SHOULD_FILTER;
    }

    private AbTestingRoute getAbRoutingInfo(String serviceName) {

        ResponseEntity<ServerResponse> restExchange;
        AbTestingRoute abTestingRoute = new AbTestingRoute();
        try {
            restExchange = restTemplate.exchange(
                    "http://specialroutesservice/v1/routes/abtestings/{serviceName}",
                    HttpMethod.GET,
                    null, ServerResponse.class, serviceName);
            ServerResponse body = restExchange.getBody();
            abTestingRoute.optionProperties((Map<String, Object>) Objects.requireNonNull(body).getData());
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw ex;
        }
        return abTestingRoute;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("SpecialRoutesFilter 处理请求响应: {}, 关联ID: {}", ctx.getRequest().getRequestURI(), filterUtil.getCorrelationId());

        String method = ctx.getRequest().getMethod();

        AbTestingRoute abTestingRoute = null;
        // 只处理 GET 请求
        if (StrUtil.equals(method, "GET")) {
            abTestingRoute = getAbRoutingInfo(filterUtil.getServiceId());
        }

        if (abTestingRoute != null && useSpecialRoute(abTestingRoute)) {

            String serviceName = filterUtil.getServiceId();
            if (StrUtil.isEmpty(serviceName)) {
                return null;
            }
            log.debug("触发动态路由V2, 地址: organizationservicev2");
            ctx.set(AB_TESTING_ROUTE, abTestingRoute);
        } else {
            ctx.set(AB_TESTING_ROUTE, null);
        }

        return null;
    }

    /**
     * 判断是否动态路由
     *
     * @param testingRoute
     * @return {@link boolean}
     */
    public boolean useSpecialRoute(AbTestingRoute testingRoute) {

        int value = RandomUtil.randomInt(10) + 1;

        return testingRoute.getWeight() < value;
    }
}
