package com.niu.licenses.repository;

import com.niu.licenses.model.Organization;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 机构数据库操作对象
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 09:26]
 * @createTime [2021/03/03 09:26]
 */
@Repository
public interface OrganizationRepository extends CrudRepository<Organization, String> {

}
