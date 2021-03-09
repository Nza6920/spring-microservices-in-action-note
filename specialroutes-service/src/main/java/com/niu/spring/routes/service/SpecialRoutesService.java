package com.niu.spring.routes.service;

import com.niu.spring.routes.exception.NotFoundException;
import com.niu.spring.routes.model.AbTestingRoute;
import com.niu.spring.routes.repository.AbTestingRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * 动态路由检索业务类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 14:36]
 * @createTime [2021/03/09 14:36]
 */
@Service
@AllArgsConstructor
public class SpecialRoutesService {

    private final AbTestingRepository abTestingRepository;

    /**
     * 根据服务名称查询路由
     *
     * @param serviceName 服务名称
     * @throws {@link NotFoundException} 找不到相关记录时抛出
     * @author nza
     * @createTime 2021/3/9 14:49
     * @return    {@link com.niu.spring.routes.model.AbTestingRoute}
     */
    public AbTestingRoute getRoute(String serviceName) {
        AbTestingRoute abTestingRoute = abTestingRepository.findByServiceName(serviceName);

        if (abTestingRoute == null) {
            throw new NotFoundException("找不到对应的路由", HttpStatus.NOT_FOUND.value());
        }

        return abTestingRoute;
    }
}
