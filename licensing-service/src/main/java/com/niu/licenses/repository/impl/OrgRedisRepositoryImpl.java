package com.niu.licenses.repository.impl;

import com.niu.licenses.model.Organization;
import com.niu.licenses.repository.OrgRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * 机构缓存实现类
 *
 * @author [nza]
 * @version 1.0 [2021/03/17 15:03]
 * @createTime [2021/03/17 15:03]
 */
@Repository
public class OrgRedisRepositoryImpl implements OrgRedisRepository {

    /**
     * redis 存储组织机构数据的散列名称
     */
    private final static String HASH_NAME = "organization";

    /**
     * redis 操作类
     */
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveOrg(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void updateOrg(Organization org) {
        hashOperations.put(HASH_NAME, org.getId(), org);
    }

    @Override
    public void deleteOrg(String organizationId) {
        hashOperations.delete(HASH_NAME, organizationId);
    }

    @Override
    public Object findOrg(String orgId) {
        return  hashOperations.get(HASH_NAME, orgId);
    }
}
