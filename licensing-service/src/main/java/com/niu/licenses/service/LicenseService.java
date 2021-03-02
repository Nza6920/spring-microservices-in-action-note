package com.niu.licenses.service;

import com.niu.licenses.config.ServiceConfig;
import com.niu.licenses.model.License;
import com.niu.licenses.repository.LicenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * 许可证业务类
 *
 * @author [nza]
 * @version 1.0 [2021/03/02 21:44]
 * @createTime [2021/03/02 21:44]
 */
@Service
@AllArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

    private final ServiceConfig serviceConfig;

    /**
     * 获取许可
     *
     * @param organizationId 机构ID
     * @param licenseId      许可ID
     * @return {@link com.niu.licenses.model.License}
     * @author nza
     * @createTime 2021/3/2 21:48
     */
    public License getLicense(String organizationId, String licenseId) {

        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);

        return license.setComment(serviceConfig.getExampleProperty());
    }

    /**
     * 查询组织下的许可
     *
     * @param organizationId 组织ID
     * @return {@link java.util.List<com.niu.licenses.model.License>}
     * @author nza
     * @createTime 2021/3/2 21:50
     */
    public List<License> getLicensesByOrg(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

    /**
     * 保存许可
     *
     * @param license 许可
     * @author nza
     * @createTime 2021/3/2 21:53
     */
    public void saveLicense(License license) {
        license.setId(UUID.randomUUID().toString());
        licenseRepository.save(license);
    }

    /**
     * 更新许可
     *
     * @param license 许可实体
     * @author nza
     * @createTime 2021/3/2 21:53
     */
    public void updateLicense(License license) {
        licenseRepository.save(license);
    }

    /**
     * 删除许可
     *
     * @param licese 许可实体
     * @author nza
     * @createTime 2021/3/2 21:52
     */
    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }
}
