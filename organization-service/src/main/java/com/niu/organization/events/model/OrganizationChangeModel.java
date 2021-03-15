package com.niu.organization.events.model;

import lombok.Data;

/**
 * 消息 POJO
 *
 * @author [nza]
 * @version 1.0 [2021/03/15 17:05]
 * @createTime [2021/03/15 17:05]
 */
@Data
public class OrganizationChangeModel {

    public OrganizationChangeModel(String type, String action, String organizationId, String correlationId) {
        this.type = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }

    /**
     * 类型
     */
    private String type;

    /**
     * 动作
     */
    private String action;

    /**
     * 机构ID
     */
    private String organizationId;

    /**
     * 关联ID
     */
    private String correlationId;
}
