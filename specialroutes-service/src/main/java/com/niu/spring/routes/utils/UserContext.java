package com.niu.spring.routes.utils;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户上下文
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 12:11]
 * @createTime [2021/03/07 12:11]
 */
@Data
@Accessors(chain = true)
public class UserContext {

    public static final String CORRELATION_ID = "sys-correlation-id";

    /**
     * 关联ID
     */
    private String correlationId = "";

    /**
     * token
     */
    private String authToken = "";
}
