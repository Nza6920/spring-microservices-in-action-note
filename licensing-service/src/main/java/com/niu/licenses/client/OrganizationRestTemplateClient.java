package com.niu.licenses.client;

import cn.hutool.json.JSONUtil;
import com.niu.licenses.model.Organization;
import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.repository.OrgRedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrganizationRestTemplateClient {

    private final RestTemplate restTemplate;

    private final OrgRedisRepository orgRedisRepository;

    /**
     * 尝试使用组织ID从Redis中检索Organization类
     *
     * @param organizationId
     * @return {@link Organization}
     */
    private Object checkRedisCache(String organizationId) {
        try {
            return orgRedisRepository.findOrg(organizationId);
        } catch (Exception ex) {
            log.error("查询机构缓存数据异常, 机构ID: {}, 异常信息: ", organizationId, ex);
            return null;
        }
    }

    /**
     * 缓存机构信息
     *
     * @param org 机构实体
     */
    private void cacheOrganizationObject(Organization org) {
        try {
            orgRedisRepository.saveOrg(org);
        } catch (Exception ex) {
            log.error("缓存机构信息异常, 机构ID: {}, 异常信息: ", org.getId(), ex);
        }
    }

    /**
     * 获取机构
     *
     * @param organizationId 机构ID
     * @return {@link java.lang.Object} 机构
     * @author nza
     * @createTime 2021/3/4 15:57
     */
    public Object getOrganization(String organizationId) {

        // 检查缓存中是否存在
        Object org = checkRedisCache(organizationId);
        if (org != null) {
            log.debug("读取到机构缓存数据: {}", org);
            return org;
        }

        // 使用服务名称传递
//        String uri = "http://organizationservice/v1/organizations/{organizationId}";

        // 使用网关传递
        String uri = "http://zuulservice/api/organizationservice/v1/organizations/{organizationId}";

        ResponseEntity<ServerResponse> restExchange = restTemplate.exchange(uri, HttpMethod.GET, null, ServerResponse.class, organizationId);

        ServerResponse body = restExchange.getBody();
        Object res = (body == null || !body.isSuccess()) ? null : body.getData();

        if (res != null) {
            Organization organization = JSONUtil.toBean(JSONUtil.toJsonStr(res), Organization.class);
            cacheOrganizationObject(organization);
            log.debug("机构数据缓存成功: {}", organization);
        }

        return res;
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
//        String uri = "http://organizationservice/v1/organizations";

        // 使用网关传递
        String uri = "http://zuulservice/api/organizationservice/v1/organizations";
        ResponseEntity<ServerResponse> restExchange = restTemplate.exchange(uri, HttpMethod.GET, null, ServerResponse.class);

        ServerResponse body = restExchange.getBody();
        return (body == null || !body.isSuccess()) ? null : body.getData();
    }
}
