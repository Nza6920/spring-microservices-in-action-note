package com.niu.licenses.client;

import com.niu.licenses.pojo.ServerResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public Object getOrganization(String organizationId) {

        // 使用服务名称传递
        String uri = "http://organizationservice/v1/organizations/{organizationId}";
        ResponseEntity<ServerResponse> restExchange = restTemplate.exchange(uri, HttpMethod.GET, null, ServerResponse.class, organizationId);

        ServerResponse body = restExchange.getBody();
        return (body == null || !body.isSuccess()) ? null : body.getData();
    }
}
