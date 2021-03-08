package com.niu.licenses.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义 Filter
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 12:24]
 * @createTime [2021/03/07 12:24]
 */
@Component
@Slf4j
public class CustomFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        log.debug("自定义Filter doFilter() Running...");

//        HttpServletRequest servletRequest = (HttpServletRequest) req;
        // 设置当前线程上下文
//        UserContextHolder.getContext().setCorrelationId(servletRequest.getHeader(UserContext.CORRELATION_ID));
//        UserContextHolder.getContext().setUserId(servletRequest.getHeader(UserContext.USER_ID));
//        UserContextHolder.getContext().setAuthToken(servletRequest.getHeader(UserContext.AUTH_TOKEN));
//        UserContextHolder.getContext().setOrgId(servletRequest.getHeader(UserContext.ORG_ID));

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        log.debug("自定义Filter doFilter() destroy...");
    }
}
