package com.niu.spring.zuul.model;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 实体类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 14:39]
 * @createTime [2021/03/09 14:39]
 */
@Data
@Accessors(chain = true)
@Slf4j
public class AbTestingRoute {

    /**
     * 服务名称
     */
    String serviceName;

    /**
     * 状态
     */
    String active;

    /**
     * 地址
     */
    String endpoint;

    /**
     * 权重
     */
    Integer weight;


    /**
     * 目标服务
     */
    String targetServiceName;

    /**
     * 当前版本
     */
    String currentVersion;

    /**
     * 目标版本
     */
    String targetVersion;

    /**
     * 设置属性
     *
     * @param maps 属性map
     * @throws {@link IllegalAccessException}
     */
    public void optionProperties(Map<String, Object> maps) {

        if (maps == null || maps.isEmpty()) {
            return;
        }

        Field[] declaredFields = AbTestingRoute.class.getDeclaredFields();

        for (Field field : declaredFields) {
            Object res = maps.get(field.getName());
            if (res != null) {
                try {
                    field.set(this, res);
                } catch (Exception e) {
                    log.error("设置对象属性异常, field: {}, value: {}", field.getName(), res);
                }
            }
        }
    }
}
