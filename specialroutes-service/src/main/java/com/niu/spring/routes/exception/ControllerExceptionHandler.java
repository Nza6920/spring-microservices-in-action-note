package com.niu.spring.routes.exception;

import com.niu.spring.routes.pojo.ResponseCode;
import com.niu.spring.routes.pojo.ServerResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * 统一异常处理
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 14:55]
 * @createTime [2021/03/09 14:55]
 */
@ControllerAdvice(basePackages = {"com.niu.spring.routes.controller.*"}, annotations = {RestController.class})
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ServerResponse<Object> notFoundExceptionHandler(NotFoundException ex) {

        log.error("统一异常拦截: ", ex);

        return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ServerResponse<Object> exceptionHandler(NotFoundException ex) {

        log.error("统一异常拦截: ", ex);

        return ServerResponse.createByError();
    }
}
