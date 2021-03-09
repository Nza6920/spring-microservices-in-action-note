package com.niu.spring.routes.repository;

import com.niu.spring.routes.model.AbTestingRoute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 动态路由仓储类
 *
 * @author [nza]
 * @version 1.0 [2021/03/09 14:37]
 * @createTime [2021/03/09 14:37]
 */
@Repository
public interface AbTestingRepository extends CrudRepository<AbTestingRoute, String> {

    /**
     * 根据服务名称查询
     *
     * @param serviceName 服务名
     * @author nza
     * @createTime 2021/3/9 14:42
     * @return    {@link com.niu.spring.routes.model.AbTestingRoute}
     */
    AbTestingRoute findByServiceName(String serviceName);
}
