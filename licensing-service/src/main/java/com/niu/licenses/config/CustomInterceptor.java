package com.niu.licenses.config;

import com.niu.licenses.utils.UserContext;
import com.niu.licenses.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <功能简述>
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 13:09]
 * @createTime [2021/03/07 13:09]
 */
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {

        log.debug("设置线程本地变量....");

        // 设置当前线程上下文
        UserContextHolder.getContext().setCorrelationId(req.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(req.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(req.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getContext().setOrgId(req.getHeader(UserContext.ORG_ID));

        log.debug("自定义请求拦截器 Correlation ID: {}", UserContextHolder.getContext().getCorrelationId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        log.debug("删除线程本地变量...");
        UserContextHolder.remove();
    }
}
