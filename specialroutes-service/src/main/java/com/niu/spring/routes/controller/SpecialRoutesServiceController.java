package com.niu.spring.routes.controller;

import com.niu.spring.routes.model.AbTestingRoute;
import com.niu.spring.routes.pojo.ServerResponse;
import com.niu.spring.routes.service.SpecialRoutesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 动态路由检索控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 14:34]
 * @createTime [2021/03/09 14:34]
 */
@RestController
@RequestMapping("v1/routes/")
@AllArgsConstructor
public class SpecialRoutesServiceController {

    private final SpecialRoutesService specialRoutesService;

    /**
     * 查询服务路由
     *
     * @param serviceName 服务名称
     * @return {@link com.niu.spring.routes.model.AbTestingRoute}
     * @author nza
     * @createTime 2021/3/9 14:52
     */
    @RequestMapping(value = "abtestings/{serviceName}", method = RequestMethod.GET)
    public ServerResponse<AbTestingRoute> abstestings(@PathVariable("serviceName") String serviceName) {

        return ServerResponse.createBySuccess(specialRoutesService.getRoute(serviceName));
    }
}
