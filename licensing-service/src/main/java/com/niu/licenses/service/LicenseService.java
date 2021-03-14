package com.niu.licenses.service;

import cn.hutool.core.util.IdUtil;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niu.licenses.client.OrganizationDiscoveryClient;
import com.niu.licenses.client.OrganizationFeignClient;
import com.niu.licenses.client.OrganizationRestTemplateClient;
import com.niu.licenses.config.ServiceConfig;
import com.niu.licenses.constant.ClientType;
import com.niu.licenses.model.License;
import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.repository.LicenseRepository;
import com.niu.licenses.utils.UserContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


/**
 * 许可证业务类
 *
 * @author [nza]
 * @version 1.0 [2021/03/02 21:44]
 * @createTime [2021/03/02 21:44]
 */
@Service
@Slf4j
@AllArgsConstructor
public class LicenseService {

    private final LicenseRepository licenseRepository;

    private final ServiceConfig serviceConfig;

    private final OrganizationFeignClient organizationFeignClient;

    private final OrganizationRestTemplateClient organizationRestClient;

    private final OrganizationDiscoveryClient organizationDiscoveryClient;

    private Object retrieveOrgInfo(String organizationId, String clientType) {

        Object organization = null;

        if (ClientType.FEIGN.equals(clientType)) {
            log.debug("I am using the feign client");
            ServerResponse serverResponse = organizationFeignClient.getOrganization(organizationId);
            if (serverResponse != null && serverResponse.isSuccess()) {
                organization = serverResponse.getData();
            }
        } else if (ClientType.REST.equals(clientType)) {
            log.debug("I am using the rest client");
            organization = organizationRestClient.getOrganization(organizationId);
        } else if (ClientType.DISCOVERY.equals(clientType)) {
            log.debug("I am using the discovery client");
            organization = organizationDiscoveryClient.getOrganization(organizationId);
        } else {
            organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    /**
     * 获取许可
     * <p>
     * {@link https://github.com/Netflix/Hystrix/wiki/Configuration#circuitBreaker.requestVolumeThreshold}
     * </p>
     *
     * @param organizationId 机构ID
     * @param licenseId      许可ID
     * @param clientType     客户端类型
     * @return {@link com.niu.licenses.model.License}
     * @author nza
     * @createTime 2021/3/2 21:48
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
    },
            fallbackMethod = "buildFallbackLicense",
            threadPoolKey = "license",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public License getLicense(String organizationId, String licenseId, String clientType) {

        try {
            log.debug("Service[获取许可] Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

            License license = licenseRepository.findByOrganizationIdAndId(organizationId, licenseId);

            Object org = retrieveOrgInfo(organizationId, clientType);

            return license.setOrganization(org);
        } finally {
            log.debug("删除 Hystrix 线程本地变量...");
            UserContextHolder.remove();
        }
    }

    private License buildFallbackLicense(String organizationId, String licenseId, String clientType) {

        log.info("执行后备方法: buildFallbackLicense");

        License license = new License();
        license.setProductName("This is temp License")
                .setOrganizationId(organizationId)
                .setId(licenseId);

        return license;
    }

    /**
     * 查询组织下的许可
     * <p>
     *
     * @param organizationId 组织ID
     * @param clientType     客户端类型
     * @return {@link java.util.List<com.niu.licenses.model.License>}
     * @HystrixCommand 开启 hystrix 熔断器
     * <p/>
     * @author nza
     * @createTime 2021/3/2 21:50
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
    },
            threadPoolKey = "licensesByOrg",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public List<License> getLicensesByOrg(String organizationId, String clientType) {

        try {
            log.debug("Service[查询组织下的许可] Correlation id: {}", UserContextHolder.getContext().getCorrelationId());

            // 随机睡眠
            // randomlyRunLong();

            List<License> licenses = licenseRepository.findByOrganizationId(organizationId);

            if (licenses != null && !licenses.isEmpty()) {
                Object org = retrieveOrgInfo(organizationId, clientType);
                for (License license : licenses) {
                    license.setOrganization(org);
                }
            }
            return licenses;
        } finally {
            log.debug("删除 Hystrix 线程本地变量...");
            UserContextHolder.remove();
        }
    }

    /**
     * 后备方法
     *
     * @param organizationId 机构ID
     * @return {@link java.util.List<com.niu.licenses.model.License>} 许可证列表
     * @createTime 2021/3/5 15:39
     */
    private List<License> buildFallbackLicenseList(String organizationId, String clientType) {

        log.info("执行后备方法: buildFallbackLicenseList");

        List<License> fallbackList = Lists.newArrayList();
        License license = new License()
                .setId("0000000-00-00000")
                .setOrganizationId(organizationId)
                .setProductName("Sorry no licensing information currently available");
        fallbackList.add(license);

        return fallbackList;
    }

    /**
     * 随机睡眠
     *
     * @author nza
     * @createTime 2021/3/4 22:03
     */
    private void randomlyRunLong() {
        Random random = new Random();

        int randomNum = random.nextInt(3) + 1;

        if (randomNum == 3) {
            log.info("开始睡眠: {}s", randomNum);
            sleep();
        }
    }

    /**
     * 睡眠 11s
     *
     * @author nza
     * @createTime 2021/3/4 22:02
     */
    private void sleep() {
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存许可
     *
     * @param license 许可
     * @author nza
     * @createTime 2021/3/2 21:53
     */
    public void saveLicense(License license) {
        license.setId(IdUtil.simpleUUID())
                .setComment(serviceConfig.getExampleProperty());
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
     * @param license 许可实体
     * @author nza
     * @createTime 2021/3/2 21:52
     */
    public void deleteLicense(License license) {
        licenseRepository.delete(license);
    }
}
