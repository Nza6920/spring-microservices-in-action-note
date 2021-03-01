package com.niu.licenses.controller;

import com.niu.licenses.model.License;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 许可证业务控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 14:40]
 * @createTime [2021/03/01 14:40]
 */
@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {

    /**
     * 获取许可证
     *
     * @param licensesId     许可证ID
     * @param organizationId 组织ID
     * @return {@link License}
     */
    @GetMapping("/licenses/{licensesId}")
    public License getLicenses(@PathVariable String licensesId, @PathVariable String organizationId) {
        return new License()
                .setId(licensesId)
                .setOrganizationId(organizationId)
                .setProductName("Niu")
                .setLicenseType("All");
    }
}
