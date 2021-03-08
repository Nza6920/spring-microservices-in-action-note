package com.niu.licenses.client;

import com.niu.licenses.pojo.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Netflix Feign 客户端
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 21:30]
 * @createTime [2021/03/03 21:30]
 */
@FeignClient("organizationservice")
public interface OrganizationFeignClient {

    /**
     * 获取机构
     *
     * @param organizationId 机构ID
     * @author nza
     * @createTime 2021/3/3 21:33
     * @return    {@link com.niu.licenses.model.Organization} 机构
     */
    @GetMapping(value = "/v1/organizations/{organizationId}", consumes = "application/json")
    ServerResponse<Object> getOrganization(@PathVariable("organizationId") String organizationId);


    /**
     * 获取机构列表
     *
     * @author nza
     * @createTime 2021/3/3 21:33
     * @return    {@link com.niu.licenses.model.Organization} 机构
     */
    @GetMapping(value = "/v1/organizations")
    ServerResponse<Object> findAllOrganization();
}
