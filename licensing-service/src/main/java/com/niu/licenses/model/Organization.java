package com.niu.licenses.model;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 组织
 *
 * @author Zian.Niu
 */
@Data
@Accessors(chain = true)
public class Organization {

    /**
     * ID
     */
    String id;

    /**
     * 名称
     */
    String name;

    /**
     * 联系人
     */
    String contactName;

    /**
     * 联系Email
     */
    String contactEmail;

    /**
     * 联系电话
     */
    String contactPhone;
}
