package com.niu.licenses.repository;

import com.niu.licenses.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <功能简述>
 *
 * @author [nza]
 * @version 1.0 [2021/03/02 21:35]
 * @createTime [2021/03/02 21:35]
 */
@Repository
public interface LicenseRepository extends CrudRepository<License, String> {

    /**
     * 查询机构下的许可
     *
     * @param organizationId 机构ID
     * @author nza
     * @createTime 2021/3/2 21:37
     * @return    {@link java.util.List<com.niu.licenses.model.License>}
     */
    List<License> findByOrganizationId(String organizationId);

    /**
     * 查询许可
     *
     * @param organizationId 机构ID
     * @param licenseId      许可证ID
     * @author nza
     * @createTime 2021/3/2 21:41
     * @return    {@link com.niu.licenses.model.License}
     */
    License findByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
