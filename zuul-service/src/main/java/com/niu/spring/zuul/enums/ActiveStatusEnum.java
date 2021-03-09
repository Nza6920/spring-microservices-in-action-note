package com.niu.spring.zuul.enums;

/**
 * 状态枚举
 *
 * @version 1.0 [2021/03/09 21:13]
 * @author [nza]
 * @createTime [2021/03/09 21:13]
 */
public enum ActiveStatusEnum {

    /**
     * 枚举状态
     */
    ACTIVE(2, "激活"),
    UN_ACTIVE(1, "禁用");

    ActiveStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 值
     */
    private final Integer value;

    /**
     * 描述
     */
    private final String desc;
}
