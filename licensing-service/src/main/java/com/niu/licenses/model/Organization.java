package com.niu.licenses.model;


import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 机构
 *
 * @author Zian.Niu
 */
@Data
@Accessors(chain = true)
public class Organization implements Serializable {

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
