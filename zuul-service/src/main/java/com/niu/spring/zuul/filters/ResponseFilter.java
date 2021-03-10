package com.niu.spring.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * 后置过滤器
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 09:42]
 * @createTime [2021/03/09 09:42]
 */
@Component
@Slf4j
@AllArgsConstructor
public class ResponseFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    private final FilterUtil filterUtil;

    /**
     * 后置过滤器
     *
     * @return {@link String} 过滤器类型
     */
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
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

    /**
     * 拦截器业务逻辑
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("ResponseFilter 处理请求响应: {}, 关联ID: {}", ctx.getRequest().getRequestURI(), filterUtil.getCorrelationId());

        // 将 关联ID 携带在响应头中
        ctx.getResponse()
                .addHeader(FilterUtil.CORRELATION_ID, filterUtil.getCorrelationId());

        return null;
    }
}
