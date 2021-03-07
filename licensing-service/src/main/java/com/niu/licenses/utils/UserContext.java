package com.niu.licenses.utils;

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

    public static final String CORRELATION_ID = "niu-correlation-id";
    public static final String AUTH_TOKEN = "niu-auth-token";
    public static final String USER_ID = "niu-user-id";
    public static final String ORG_ID = "niu-org-id";

    /**
     * 关联ID
     */
    private String correlationId = "";

    /**
     * token
     */
    private String authToken = "";

    /**
     * 用户ID
     */
    private String userId = "";

    /**
     * 机构ID
     */
    private String orgId = "";
}
