package com.niu.licenses.utils;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpHeaders;

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
    public static final String AUTH_TOKEN = HttpHeaders.AUTHORIZATION;
    public static final String USER_ID = "sys-user-id";
    public static final String ORG_ID = "sys-org-id";

    /**
     * HEADER
     */
    public static final String HEADER_AUTHORIZATION = "authorization";

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
