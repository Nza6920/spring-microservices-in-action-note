package com.niu.spring.routes.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 没有找到对应的记录
 *
 * @version 1.0 [2021/03/09 14:46]
 * @author [nza]
 * @createTime [2021/03/09 14:46]
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {

    private final Integer code;

    public NotFoundException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
