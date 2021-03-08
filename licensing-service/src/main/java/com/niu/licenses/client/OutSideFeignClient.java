package com.niu.licenses.client;

import com.niu.licenses.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 外部服务客户端
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 21:30]
 * @createTime [2021/03/03 21:30]
 */
@FeignClient("sidecarservice")
public interface OutSideFeignClient {

    /**
     * 外部测试服务
     *
     * @return {@link Organization} 机构
     * @author nza
     * @createTime 2021/3/3 21:33
     */
    @GetMapping(value = "/outside/home", consumes = "application/json")
    Object testOutSide();
}
