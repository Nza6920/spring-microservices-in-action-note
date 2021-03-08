package com.niu.licenses.controller;

import com.niu.licenses.model.License;
import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.service.LicenseService;
import com.niu.licenses.utils.UserContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LicenseServiceController {

    private final LicenseService licenseService;

    /**
     * 获取许可证
     *
     * @param licensesId     许可证ID
     * @param organizationId 组织ID
     * @return {@link License}
     */
    @GetMapping("/{licensesId}/{clientType}")
    public ServerResponse<License> getLicenses(@PathVariable String licensesId,
                                               @PathVariable String organizationId,
                                               @PathVariable String clientType) {

        log.debug("Controller[获取许可证], Correlation ID: {}", UserContextHolder.getContext().getCorrelationId());
        return ServerResponse.createBySuccess(licenseService.getLicense(organizationId, licensesId, clientType));
    }

    /**
     * 查询机构所有许可
     *
     * @param organizationId 机构ID
     * @return {@link java.util.List<com.niu.licenses.model.License>}
     * @author nza
     * @createTime 2021/3/2 21:56
     */
    @GetMapping("/{clientType}")
    public ServerResponse<List<License>> getLicensesByOrg(@PathVariable String organizationId,
                                                          @PathVariable String clientType) {
        log.debug("Controller[查询机构所有许可], Correlation ID: {}", UserContextHolder.getContext().getCorrelationId());
        return ServerResponse.createBySuccess(licenseService.getLicensesByOrg(organizationId, clientType));
    }
}
