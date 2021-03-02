package com.niu.licenses.controller;

import com.niu.licenses.model.License;
import com.niu.licenses.service.LicenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public License getLicenses(@PathVariable String licensesId, @PathVariable String organizationId) {
        return licenseService.getLicense(organizationId, licensesId);
    }

    /**
     * 查询机构所有许可
     *
     * @param organizationId 机构ID
     * @return {@link java.util.List<com.niu.licenses.model.License>}
     * @author nza
     * @createTime 2021/3/2 21:56
     */
    @GetMapping(value = "/")
    public List<License> getLicenses(@PathVariable("organizationId") String organizationId) {
        return licenseService.getLicensesByOrg(organizationId);
    }

    /**
     * 更新许可
     *
     * @param licenseId 许可ID
     * @return {@link java.lang.String}
     * @author nza
     * @createTime 2021/3/2 21:59
     */
    @RequestMapping(value = "{licenseId}", method = RequestMethod.PUT)
    public String updateLicenses(@PathVariable("licenseId") String licenseId, @PathVariable("organizationId") String organizationId) {

        License license = licenseService.getLicense(organizationId, licenseId);
        license.setComment("编辑了许可");

        licenseService.updateLicense(license);
        return "This is the put";
    }

    /**
     * 保存许可
     *
     * @param license 许可
     * @author nza
     * @createTime 2021/3/2 21:58
     */
    @PostMapping("/")
    public void saveLicenses(@RequestBody License license) {
        licenseService.saveLicense(license);
    }

    /**
     * 删除许可
     *
     * @param licenseId 许可ID
     * @return java.lang.String
     * @author nza
     * @createTime 2021/3/2 21:57
     */
    @DeleteMapping(value = "{licenseId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteLicenses(@PathVariable("licenseId") String licenseId, @PathVariable String organizationId) {

        License license = licenseService.getLicense(organizationId, licenseId);
        licenseService.deleteLicense(license);

        return "This is the Delete";
    }
}
