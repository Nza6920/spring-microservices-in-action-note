package com.niu.licenses.service;

import cn.hutool.core.util.RandomUtil;
import com.niu.licenses.BaseTest;
import com.niu.licenses.config.ServiceConfig;
import com.niu.licenses.model.License;
import com.niu.licenses.model.Organization;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 许可证服务测试类
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 09:32]
 * @createTime [2021/03/03 09:32]
 */
@Slf4j
public class LicenseServiceTest extends BaseTest {

    @Resource
    private LicenseService licenseService;

    @Resource
    private OrganizationService organizationService;

    @Resource
    private ServiceConfig serviceConfig;

    private static final String[] LICENSE_TYPES = new String[]{"official", "personal", "org"};

    @Test
    public void mockData() {

        log.info("start mock licenses");

        List<Organization> organizations = organizationService.findAllOrganization();
        for (int i = 0; i < organizations.size(); i++) {
            License license = new License()
                    .setLicenseAllocated(i + 10)
                    .setLicenseMax(i + 100)
                    .setLicenseType(LICENSE_TYPES[RandomUtil.randomInt(LICENSE_TYPES.length)])
                    .setOrganizationId(organizations.get(i).getId())
                    .setProductName("产品" + i)
                    .setComment(serviceConfig.getExampleProperty());
            licenseService.saveLicense(license);
        }

        log.info("mock licenses success");
    }
}
