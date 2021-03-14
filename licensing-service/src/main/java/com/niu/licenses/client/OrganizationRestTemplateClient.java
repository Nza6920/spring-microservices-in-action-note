package com.niu.licenses.client;

import cn.hutool.core.util.StrUtil;
import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.utils.UserContextHolder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedClientException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * 启用了 RestTemplate 的 Spring DiscoveryClient
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 21:33]
 * @createTime [2021/03/03 21:33]
 */
@Component
@AllArgsConstructor
public class OrganizationRestTemplateClient {

    private final RestTemplate restTemplate;

    /**
     * 获取机构
     *
     * @param organizationId 机构ID
     * @return {@link java.lang.Object} 机构
     * @author nza
     * @createTime 2021/3/4 15:57
     */
    public Object getOrganization(String organizationId) {

        // 使用服务名称传递
        String uri = "http://organizationservice/v1/organizations/{organizationId}";
        ResponseEntity<ServerResponse> restExchange = restTemplate.exchange(uri, HttpMethod.GET, null, ServerResponse.class, organizationId);

        ServerResponse body = restExchange.getBody();
        return (body == null || !body.isSuccess()) ? null : body.getData();
    }

    /**
     * 获取机构列表
     *
     * @return {@link java.lang.Object} 机构
     * @author nza
     * @createTime 2021/3/4 15:57
     */
    public Object findAllOrganization() {

        // 使用服务名称传递
        String uri = "http://organizationservice/v1/organizations";
        ResponseEntity<ServerResponse> restExchange = restTemplate.exchange(uri, HttpMethod.GET, null, ServerResponse.class);

        ServerResponse body = restExchange.getBody();
        return (body == null || !body.isSuccess()) ? null : body.getData();
    }
}
