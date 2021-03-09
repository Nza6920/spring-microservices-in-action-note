package com.niu.organization.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.niu.organization.model.Organization;

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
