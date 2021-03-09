package com.niu.spring.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.niu.spring.zuul.model.AbTestingRoute;
import com.niu.spring.zuul.pojo.ServerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 动态路由过滤器
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 16:00]
 * @createTime [2021/03/09 16:00]
 */
@Component
@AllArgsConstructor
@Slf4j
public class SpecialRoutesFilter extends ZuulFilter {

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
        return FilterUtil.ROUTE_FILTER_TYPE;
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
            abTestingRoute.optionProperties((Map<String, Object>) body.getData());
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
        AbTestingRoute abRoutingInfo = getAbRoutingInfo(filterUtil.getServiceId());

        return null;
    }
}
