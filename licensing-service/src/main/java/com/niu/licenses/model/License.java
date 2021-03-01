package com.niu.licenses.model;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 许可证
 *
 * @author Zian.Niu
 */
@Data
@Accessors(chain = true)
public class License {

    /**
     * ID
     */
    private String id;

    /**
     * 组织ID
     */
    private String organizationId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 许可证类型
     */
    private String licenseType;
}
