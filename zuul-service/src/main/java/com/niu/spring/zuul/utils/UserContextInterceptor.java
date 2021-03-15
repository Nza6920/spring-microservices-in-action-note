package com.niu.spring.zuul.utils;

import com.netflix.zuul.context.RequestContext;
import com.niu.spring.zuul.filters.FilterUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 用户上下文拦截器
 * restTemplate 拦截器
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 12:30]
 * @createTime [2021/03/07 12:30]
 */
@Slf4j
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest req, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {

        RequestContext ctx = RequestContext.getCurrentContext();
        String correlationId = ctx.getRequest().getHeader(FilterUtil.CORRELATION_ID);
        String authToken = ctx.getRequest().getHeader(HttpHeaders.AUTHORIZATION);

        log.debug("UserContextInterceptor 请求拦截器");

        HttpHeaders headers = req.getHeaders();
        headers.add(FilterUtil.CORRELATION_ID, correlationId);
        headers.add(HttpHeaders.AUTHORIZATION, authToken);

        return execution.execute(req, bytes);
    }
}
