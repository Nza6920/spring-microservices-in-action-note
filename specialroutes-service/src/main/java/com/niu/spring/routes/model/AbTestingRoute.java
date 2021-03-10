package com.niu.spring.routes.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 实体类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 14:39]
 * @createTime [2021/03/09 14:39]
 */
@Entity
@Table(name = "abtesting")
@Data
@Accessors(chain = true)
public class AbTestingRoute {

    /**
     * 服务名称
     */
    @Id
    @Column(name = "service_name", nullable = false)
    String serviceName;

    /**
     * 状态
     */
    @Column(name = "active", nullable = false)
    String active;

    /**
     * 地址
     */
    @Column(name = "endpoint", nullable = false)
    String endpoint;

    /**
     * 权重
     */
    @Column(name = "weight", nullable = false)
    Integer weight;

    /**
     * 目标服务
     */
    @Column(name = "target_service_name", nullable = false)
    String targetServiceName;

    /**
     * 当前版本
     */
    @Column(name = "current_version", nullable = false)
    String currentVersion;

    /**
     * 目标版本
     */
    @Column(name = "target_version", nullable = false)
    String targetVersion;
}
