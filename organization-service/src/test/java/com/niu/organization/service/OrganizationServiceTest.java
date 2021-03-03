package com.niu.organization.service;

import cn.hutool.core.util.RandomUtil;
import com.niu.organization.BaseTest;
import com.niu.organization.model.Organization;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * 机构业务测试类
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 09:41]
 * @createTime [2021/03/03 09:41]
 */
@Slf4j
public class OrganizationServiceTest extends BaseTest {

    @Resource
    private OrganizationService organizationService;

    @Test
    public void mockData() {
        log.info("start mock organization....");
        for (int i = 20; i > 0; i--) {
            Organization organization = new Organization()
                    .setName("机构" + i)
                    .setContactName("联系人" + i)
                    .setContactPhone(RandomUtil.randomNumbers(11))
                    .setContactEmail(RandomUtil.randomNumbers(5) + "@google.com");
            organizationService.saveOrganization(organization);
        }
        log.info("mock organization success");
    }
}
