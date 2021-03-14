package com.niu.spring.zuul.filters;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.niu.spring.zuul.config.ServiceConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * 前置过滤器
 * 检查 Header 是否存在 sys-correlation-id
 * 不存在则在首部添加
 *
 * @author [nza]
 * @version 1.0 [2021/03/08 22:09]
 * @createTime [2021/03/08 22:09]
 */
@Component
@Slf4j
@AllArgsConstructor
public class TrackingFilter extends ZuulFilter {

    private final FilterUtil filterUtil;
    private final ServiceConfig serviceConfig;

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;

    /**
     * 获取机构ID
     *
     * @return {@link String} 机构ID
     */
    private String getOrganizationId() {
        String result = StrUtil.EMPTY;
        if (filterUtil.getAuthToken() != null) {
            String authToken = filterUtil.getAuthToken().replace("Bearer ", "");
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(serviceConfig.getJwtSignKey().getBytes(StandardCharsets.UTF_8))
                        .parseClaimsJws(authToken).getBody();
                result = (String) claims.get("organizationId");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 告诉 Zuul 该过滤器的类型 (前置 | 路由 | 后置)
     *
     * @return {@link java.lang.String}
     * @createTime 2021/3/8 22:31
     */
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
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
     * 过滤器逻辑代码
     *
     * @return {@link java.lang.Object}
     * @author nza
     * @createTime 2021/3/8 22:40
     */
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        log.debug("TrackingFilter 处理请求: {}", ctx.getRequest().getRequestURI());

        // 已存在 关联ID 不做处理
        if (isCorrelationIdPresent()) {
            log.debug("TrackingFilter 已存在头部[{}]: {}", FilterUtil.CORRELATION_ID, filterUtil.getCorrelationId());
        } else {
            // 设置关联ID
            filterUtil.setCorrelationId(generateCorrelationId());
            log.debug("TrackingFilter 已设置头部[{}]: {}", FilterUtil.CORRELATION_ID, filterUtil.getCorrelationId());
        }

        log.info("机构ID: " + getOrganizationId());

        return null;
    }

    /**
     * 判断请求头部是否存在 {@link FilterUtil#CORRELATION_ID}
     *
     * @return boolean
     * @author nza
     * @createTime 2021/3/8 22:36
     */
    private boolean isCorrelationIdPresent() {
        return filterUtil.getCorrelationId() != null;
    }

    /**
     * 生成 {@link FilterUtil#CORRELATION_ID}
     *
     * @return {@link java.lang.String}
     * @author nza
     */
    private String generateCorrelationId() {
        return UUID.fastUUID().toString(true);
    }
}
