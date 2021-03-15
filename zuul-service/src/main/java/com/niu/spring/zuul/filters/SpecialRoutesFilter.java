package com.niu.spring.zuul.filters;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.niu.spring.zuul.model.AbTestingRoute;
import com.niu.spring.zuul.pojo.ServerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpRequest;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.route.SimpleHostRoutingFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 动态路由过滤器
 * 参考 {@link SimpleHostRoutingFilter}
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 16:00]
 * @createTime [2021/03/09 16:00]
 */
//@Component
@Slf4j
@AllArgsConstructor
public class SpecialRoutesFilter extends ZuulFilter {

    private static final int FILTER_ORDER = 1;
    private static final boolean SHOULD_FILTER = true;
    private static final String FORWARD_VERSION = "v2";

    private final FilterUtil filterUtil;

    private final RestTemplate restTemplate;

    private final ProxyRequestHelper helper;

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

        AbTestingRoute abTestingRoute = getAbRoutingInfo(filterUtil.getServiceId());

        if (abTestingRoute != null && useSpecialRoute(abTestingRoute)) {

            String serviceName = filterUtil.getServiceId();
            if (StrUtil.isEmpty(serviceName)) {
                return null;
            }

            String route = buildRouteString(ctx.getRequest().getRequestURI(), abTestingRoute.getEndpoint(), serviceName);

            log.debug("触发动态路由, 地址: {}", route);

            // 设置动态路由标记
            filterUtil.setSpecialRouteFlag(true);

            // 转发请求
            return forwardToSpecialRoute(route);

        } else {
            // 设置动态路由标记
            filterUtil.setSpecialRouteFlag(false);
        }

        return null;
    }

    /**
     * 转发请求
     *
     * @param route 转发路由地址
     * @return
     */
    private HttpResponse forwardToSpecialRoute(String route) {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        // 创建转发的头部副本
        MultiValueMap<String, String> headers = this.helper
                .buildZuulRequestHeaders(request);

        // 创建转发的参数副本
        MultiValueMap<String, String> params = this.helper
                .buildZuulRequestQueryParams(request);
        String verb = getVerb(request);

        // 创建转发的主体副本
        InputStream requestEntity = getRequestBody(request);
        if (request.getContentLength() < 0) {
            context.setChunkedRequestBody();
        }

        this.helper.addIgnoredHeaders();
        CloseableHttpClient httpClient = null;
        HttpResponse response;

        try {
            httpClient = HttpClients.createDefault();

            // 转发请求
            response = forward(httpClient, verb, route, request, headers, params, requestEntity);

            // 将转发后的响应保存会zuul服务器
            setResponse(response);

            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException ex) {
                log.error("关闭 httpClient 客户端异常: ", ex);
            }
        }

        return null;
    }

    private String getVerb(HttpServletRequest request) {
        String sMethod = request.getMethod();
        return sMethod.toUpperCase();
    }

    private InputStream getRequestBody(HttpServletRequest request) {
        InputStream requestEntity = null;
        try {
            requestEntity = request.getInputStream();
        } catch (IOException ex) {
            // no requestBody is ok.
        }
        return requestEntity;
    }

    private MultiValueMap<String, String> revertHeaders(Header[] headers) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Header header : headers) {
            String name = header.getName();
            if (!map.containsKey(name)) {
                map.put(name, Lists.newArrayList());
            }
            map.get(name).add(header.getValue());
        }
        return map;
    }

    private void setResponse(HttpResponse response) throws IOException {
        RequestContext.getCurrentContext().set("zuulResponse", response);
        this.helper.setResponse(response.getStatusLine().getStatusCode(),
                response.getEntity() == null ? null : response.getEntity().getContent(),
                revertHeaders(response.getAllHeaders()));
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

    /**
     * 构建路由地址
     *
     * @param oldEndpoint 旧终点
     * @param newEndpoint 新终点
     * @param serviceName 服务名
     * @return {@link String} 目标路由
     */
    private String buildRouteString(String oldEndpoint, String newEndpoint, String serviceName) {
        int index = oldEndpoint.indexOf(serviceName);

        String strippedRoute = oldEndpoint.substring(index + serviceName.length() + FORWARD_VERSION.length() + 1);

        return String.format("%s/%s%s", newEndpoint, FORWARD_VERSION, strippedRoute);
    }

    /**
     * 获取主机
     *
     * @param url
     * @return {@link org.apache.http.HttpHost}
     * @author nza
     * @createTime 2021/3/9 22:01
     */
    private HttpHost getHttpHost(URL url) {
        return new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
    }

    /**
     * 转发请求
     *
     * @return {@link HttpResponse} http 响应
     * @throws Exception {@link Exception} 转发失败抛出
     */
    private HttpResponse forward(HttpClient httpclient,
                                 String verb,
                                 String uri,
                                 HttpServletRequest request,
                                 MultiValueMap<String, String> headers,
                                 MultiValueMap<String, String> params,
                                 InputStream requestEntity) throws Exception {

        Map<String, Object> info = this.helper.debug(verb, uri, headers, params, requestEntity);

        URL host = new URL(uri);

        HttpHost httpHost = getHttpHost(host);

        HttpRequest httpRequest;
        int contentLength = request.getContentLength();
        InputStreamEntity entity = new InputStreamEntity(requestEntity,
                contentLength,
                request.getContentType() != null ? ContentType.create(request.getContentType()) : null);
        switch (verb.toUpperCase()) {
            case "POST":
                HttpPost httpPost = new HttpPost(uri);
                httpRequest = httpPost;
                httpPost.setEntity(entity);
                break;
            case "PUT":
                HttpPut httpPut = new HttpPut(uri);
                httpRequest = httpPut;
                httpPut.setEntity(entity);
                break;
            case "PATCH":
                HttpPatch httpPatch = new HttpPatch(uri);
                httpRequest = httpPatch;
                httpPatch.setEntity(entity);
                break;
            default:
                httpRequest = new BasicHttpRequest(verb, uri);
        }

        httpRequest.setHeaders(convertHeaders(headers));

        HttpResponse zuulResponse = forwardRequest(httpclient, httpHost, httpRequest);

        this.helper.appendDebug(info, zuulResponse.getStatusLine().getStatusCode(),
                revertHeaders(zuulResponse.getAllHeaders()));

        return zuulResponse;

    }

    private HttpResponse forwardRequest(HttpClient httpclient, HttpHost httpHost, HttpRequest httpRequest) throws IOException {
        return httpclient.execute(httpHost, httpRequest);
    }

    private Header[] convertHeaders(MultiValueMap<String, String> headers) {
        List<Header> list = new ArrayList<>();
        for (String name : headers.keySet()) {
            for (String value : headers.get(name)) {
                list.add(new BasicHeader(name, value));
            }
        }
        return list.toArray(new BasicHeader[0]);
    }
}
