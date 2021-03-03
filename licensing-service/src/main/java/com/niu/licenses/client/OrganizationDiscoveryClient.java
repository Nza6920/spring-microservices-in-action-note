package com.niu.licenses.client;

import com.niu.licenses.pojo.ServerResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 组织客户端
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 17:26]
 * @createTime [2021/03/03 17:26]
 */
@Component
@AllArgsConstructor
@Slf4j
public class OrganizationDiscoveryClient {

    private final DiscoveryClient discoveryClient;

    /**
     * 获取组织服务
     *
     * @param organizationId 组织ID
     * @return {@link com.niu.licenses.model.Organization}
     * @author nza
     * @createTime 2021/3/3 17:50
     */
    public Object getOrganization(String organizationId) {

        RestTemplate restTemplate = new RestTemplate();

        // 获取组织服务所有的实例列表
        List<ServiceInstance> instances = discoveryClient.getInstances("organizationservice");

        if (instances.size() == 0) {
            return null;
        }

        // 检索要调用的服务列表
        String serviceUri = String.format("%s/v1/organizations/%s", instances.get(0).getUri().toString(), organizationId);
        log.info("SERVICE URI: {}", serviceUri);

        // 使用标准的 Spring Rest 模板类去调用服务
        ResponseEntity<ServerResponse> restExchange = restTemplate.exchange(serviceUri, HttpMethod.GET, null,
                ServerResponse.class, organizationId);

        ServerResponse body = restExchange.getBody();
        return (body == null || !body.isSuccess()) ? null : body.getData();
    }
}
