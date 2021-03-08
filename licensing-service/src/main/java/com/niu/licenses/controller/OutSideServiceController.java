package com.niu.licenses.controller;

import com.niu.licenses.pojo.ServerResponse;
import com.niu.licenses.service.OutSideService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 外部服务控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/01 14:40]
 * @createTime [2021/03/01 14:40]
 */
@RestController
@RequestMapping(value = "/v1/outsides")
@Slf4j
@AllArgsConstructor
public class OutSideServiceController {

    private final OutSideService outSideService;

    /**
     * 调用外部服务
     *
     * @return {@link java.util.List<com.niu.licenses.model.License>}
     * @author nza
     * @createTime 2021/3/2 21:56
     */
    @GetMapping()
    public ServerResponse<Object> testOutSide() {
        log.debug("调用外部服务...");
        return ServerResponse.createBySuccess(outSideService.testOutSide());
    }
}
