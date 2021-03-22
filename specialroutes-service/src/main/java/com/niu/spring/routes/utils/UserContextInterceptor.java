package com.niu.spring.routes.utils;

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
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest req, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = req.getHeaders();
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(HttpHeaders.AUTHORIZATION, UserContextHolder.getContext().getAuthToken());

        return execution.execute(req, bytes);
    }
}
