package com.niu.organization.controller;

import com.niu.organization.model.Organization;
import com.niu.organization.pojo.ServerResponse;
import com.niu.organization.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 机构控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 17:16]
 * @createTime [2021/03/03 17:16]
 */
@RestController
@RequestMapping(value = "v1/organizations")
@AllArgsConstructor
public class OrganizationServiceController {

    private final OrganizationService organizationService;

    /**
     * 根据ID获取机构
     *
     * @param organizationId 机构ID
     * @author nza
     * @createTime 2021/3/3 17:21
     * @return    {@link com.niu.organization.pojo.ServerResponse<com.niu.organization.model.Organization>}
     */
    @RequestMapping(value = "/{organizationId}", method = RequestMethod.GET)
    public ServerResponse<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        return ServerResponse.createBySuccess(organizationService.findById(organizationId));
    }

    /**
     * 新增机构
     *
     * @param org 机构实体
     * @author nza
     * @createTime 2021/3/3 17:21
     * @return    {@link com.niu.organization.pojo.ServerResponse<com.niu.organization.model.Organization>}
     */
    @PostMapping()
    public ServerResponse<Object> saveOrganization(@RequestBody Organization org) {
        organizationService.saveOrganization(org);
        return ServerResponse.createBySuccess();
    }
}
