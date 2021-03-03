package com.niu.licenses.controller;

import com.niu.licenses.model.License;
import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 许可证业务控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 14:40]
 * @createTime [2021/03/01 14:40]
 */
@RestController
@RequestMapping(value = "/v1/organizations/{organizationId}/licenses")
@AllArgsConstructor
public class LicenseServiceController {

    private final LicenseService licenseService;

    /**
     * 获取许可证
     *
     * @param licensesId     许可证ID
     * @param organizationId 组织ID
     * @return {@link License}
     */
    @GetMapping("/{licensesId}")
    public ServerResponse<License> getLicenses(@PathVariable String licensesId, @PathVariable String organizationId) {
        return ServerResponse.createBySuccess(licenseService.getLicense(organizationId, licensesId));
    }

    /**
     * 查询机构所有许可
     *
     * @param organizationId 机构ID
     * @return {@link java.util.List<com.niu.licenses.model.License>}
     * @author nza
     * @createTime 2021/3/2 21:56
     */
    @GetMapping()
    public ServerResponse<List<License>> getLicenses(@PathVariable("organizationId") String organizationId) {
        return ServerResponse.createBySuccess(licenseService.getLicensesByOrg(organizationId));
    }
}
