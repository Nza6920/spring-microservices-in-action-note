package com.niu.spring.zuul.filters;

import cn.hutool.core.util.StrUtil;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * 过滤器工具类
 *
 * @author [nza]
 * @version 1.0 [2021/03/08 22:12]
 * @createTime [2021/03/08 22:12]
 */
@Component
public class FilterUtil {

    /**
     * 请求头
     */
    public static final String CORRELATION_ID = "sys-correlation-id";
    public static final String USER_ID = "sys-user-id";
    public static final String ORG_ID = "sys-org-id";

    /**
     * 上下文实体
     */
    public static final String SERVER_ID = "serviceId";

    /**
     * 是否动态路由
     */
    public static final String SPECIAL_ROUTE_FLAG = "specialRouteFlag";
    public static final String AB_TESTING_ROUTE = "abTestingRoute";

    /**
     * 获取关联ID
     *
     * @author nza
     * @createTime 2021/3/8 22:14
     */
    public String getCorrelationId() {

        RequestContext ctx = RequestContext.getCurrentContext();

        // 先获取 Http 请求头中是否包含
        String correlationId = ctx.getRequest().getHeader(CORRELATION_ID);
        if (StrUtil.isNotEmpty(correlationId)) {
            return correlationId;
        }

        // 再从 zuul 请求头中获取
        return ctx.getZuulRequestHeaders().get(CORRELATION_ID);
    }

    /**
     * 设置 sys-correlation-id
     *
     * @param correlationId 关联ID
     * @author nza
     * @createTime 2021/3/8 22:22
     */
    public void setCorrelationId(String correlationId) {

        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(CORRELATION_ID, correlationId);
    }

    /**
     * 获取组织ID
     *
     * @return {@link java.lang.String}
     * @author nza
     * @createTime 2021/3/8 22:24
     */
    public String getOrgId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        // 先获取 Http 请求头中是否包含
        String orgId = ctx.getRequest().getHeader(ORG_ID);
        if (StrUtil.isNotEmpty(orgId)) {
            return orgId;
        }

        // 再从 zuul 请求头中获取
        return ctx.getZuulRequestHeaders().get(ORG_ID);
    }

    /**
     * 设置组织 ID
     *
     * @param orgId 组织ID
     * @author nza
     * @createTime 2021/3/8 22:25
     */
    public void setOrgId(String orgId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(ORG_ID, orgId);
    }

    /**
     * 获取用户ID
     *
     * @return {@link java.lang.String}
     * @author nza
     * @createTime 2021/3/8 22:24
     */
    public String getUserId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        // 先获取 Http 请求头中是否包含
        String userId = ctx.getRequest().getHeader(USER_ID);
        if (StrUtil.isNotEmpty(userId)) {
            return userId;
        }

        // 再从 zuul 请求头中获取
        return ctx.getZuulRequestHeaders().get(USER_ID);
    }

    /**
     * 设置用户 ID
     *
     * @param userId 用户ID
     * @author nza
     * @createTime 2021/3/8 22:25
     */
    public void setUserId(String userId) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(USER_ID, userId);
    }

    /**
     * 获取授权 token
     *
     * @return {@link java.lang.String}
     * @author nza
     * @createTime 2021/3/8 22:24
     */
    public String getAuthToken() {
        RequestContext ctx = RequestContext.getCurrentContext();

        // 先获取 Http 请求头中是否包含
        String token = ctx.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        if (StrUtil.isNotEmpty(token)) {
            return token;
        }

        // 再从 zuul 请求头中获取
        return ctx.getZuulRequestHeaders().get(HttpHeaders.AUTHORIZATION);
    }

    /**
     * 设置授权 token
     *
     * @param authToken 授权 token
     * @author nza
     * @createTime 2021/3/8 22:25
     */
    public void setAuthToken(String authToken) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.addZuulRequestHeader(HttpHeaders.AUTHORIZATION, authToken);
    }

    /**
     * 获取服务ID
     *
     * @return {@link java.lang.String}
     * @author nza
     * @createTime 2021/3/9 16:19
     */
    public String getServiceId() {
        RequestContext ctx = RequestContext.getCurrentContext();

        if (ctx.get(SERVER_ID) == null) {
            return StrUtil.EMPTY;
        }

        return ctx.get(SERVER_ID).toString();
    }

    /**
     * 设置 ServiceId
     *
     * @author nza
     * @createTime 2021/3/9 16:19
     */
    public void setServiceId(String serviceId) {
        RequestContext ctx = RequestContext.getCurrentContext();

        ctx.set(SERVER_ID, serviceId);
    }

    /**
     * 设置动态路由标记
     *
     * @author nza
     * @createTime 2021/3/9 16:19
     */
    public void setSpecialRouteFlag(boolean res) {
        RequestContext ctx = RequestContext.getCurrentContext();
        ctx.set(SPECIAL_ROUTE_FLAG, res);
    }
}
