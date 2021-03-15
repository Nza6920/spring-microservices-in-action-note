package com.niu.organization.enums;

import lombok.Getter;

/**
 * 消息类型枚举
 *
 * @version 1.0 [2021/03/15 17:43]
 * @author [nza]
 * @createTime [2021/03/15 17:43]
 */
@Getter
public enum  MessageTypeEnums {

    /**
     * 枚举值
     */
    SAVE("SAVE", "新增"),
    UPDATE("UPDATE", "编辑");

    MessageTypeEnums(String value, String des) {
        this.value = value;
        this.des = des;
    }

    /**
     * 值
     */
    private final String value;

    /**
     * 描述
     */
    private final String des;
}
