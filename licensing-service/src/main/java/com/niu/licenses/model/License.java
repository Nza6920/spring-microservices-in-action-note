package com.niu.licenses.model;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 许可证
 *
 * @author Zian.Niu
 */
@Data
@Accessors(chain = true)
@Entity
@Table(name = "license")
public class License {

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 组织ID
     */
    @Column(name = "organization_id")
    private String organizationId;

    /**
     * 产品名称
     */
    @Column(name = "product_name", nullable = false)
    private String productName;

    /**
     * 许可证类型
     */
    @Column(name = "license_type", nullable = false)
    private String licenseType;

    /**
     * 最大数量
     */
    @Column(name = "license_max", nullable = false)
    private Integer licenseMax;

    /**
     * 已分配数量
     */
    @Column(name = "license_allocated", nullable = false)
    private Integer licenseAllocated;

    /**
     * 备注
     */
    @Column(name = "comment")
    private String comment;
}
