package com.niu.licenses.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.utils.UserContext;
import com.niu.licenses.utils.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义请求拦截器
 *
 * @author [nza]
 * @version 1.0 [2021/03/07 13:09]
 * @createTime [2021/03/07 13:09]
 */
@Slf4j
public class CustomInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) {

        log.debug("设置线程本地变量....");

        // 检查 Token
        String authorization = req.getHeader(UserContext.HEADER_AUTHORIZATION);
        if (StrUtil.isEmpty(authorization)) {
            resp.setStatus(HttpStatus.UNAUTHORIZED.value());
            resp.setContentType("application/json;charset=UTF-8");
            ServerResponse<Object> serverResp = ServerResponse.createByErrorMessage("无权限");
            try (PrintWriter writer = resp.getWriter()) {
                writer.write(JSONUtil.toJsonStr(serverResp));
                writer.flush();
            } catch (IOException e) {
                log.error("获取响应 Writer 异常: ", e);
            }
            return false;
        }

        // 设置当前线程上下文
        UserContextHolder.getContext().setCorrelationId(req.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(req.getHeader(UserContext.USER_ID));
        UserContextHolder.getContext().setAuthToken(authorization);
        UserContextHolder.getContext().setOrgId(req.getHeader(UserContext.ORG_ID));

        log.debug("自定义请求拦截器 Correlation ID: {}", UserContextHolder.getContext().getCorrelationId());

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) {
        log.debug("删除线程本地变量...");
        UserContextHolder.remove();
    }
}
