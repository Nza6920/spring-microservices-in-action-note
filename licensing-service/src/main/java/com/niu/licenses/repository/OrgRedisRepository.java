package com.niu.licenses.repository;

import com.niu.licenses.model.Organization;

/**
 * 机构 Redis 操作接口
 *
 * @author [nza]
 * @version 1.0 [2021/03/17 15:00]
 * @createTime [2021/03/17 15:00]
 */
public interface OrgRedisRepository {

    /**
     * 保存机构
     *
     * @param org 机构实体
     * @author nza
     * @createTime 2021/3/17 15:00
     */
    void saveOrg(Organization org);

    /**
     * 编辑机构
     *
     * @param org 机构实体
     * @author nza
     * @createTime 2021/3/17 15:00
     */
    void updateOrg(Organization org);

    /**
     * 删除机构
     *
     * @param organization 机构
     * @author nza
     * @createTime 2021/3/17 15:01
     */
    void deleteOrg(String organization);

    /**
     * 查询机构
     *
     * @param orgId 机构ID
     * @author nza
     * @createTime 2021/3/17 15:02
     * @return    {@link com.niu.licenses.model.Organization}
     */
    Object findOrg(String orgId);
}
