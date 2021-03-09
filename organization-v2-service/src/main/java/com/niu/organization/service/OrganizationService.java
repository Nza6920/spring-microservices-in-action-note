package com.niu.organization.service;

import cn.hutool.core.util.IdUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.niu.organization.model.Organization;
import com.niu.organization.repository.OrganizationRepository;

import java.util.List;
import java.util.Optional;

/**
 * 机构业务类
 *
 * @author [nza]
 * @version 1.0 [2021/03/02 21:54]
 * @createTime [2021/03/02 21:54]
 */
@Service
@AllArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    /**
     * 保存机构
     *
     * @param organization 机构实体
     * @return {@link String} 新增的机构ID
     * @author nza
     * @createTime 2021/3/3 9:43
     */
    public String saveOrganization(Organization organization) {

        organization.setId(IdUtil.simpleUUID());
        Organization newOrg = organizationRepository.save(organization);

        return newOrg.getId();
    }

    /**
     * 根据ID获取机构
     *
     * @param id 机构ID
     * @return {@link Organization}
     * @createTime 2021/3/3 17:20
     */
    public Organization findById(String id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElse(null);
    }

    /**
     * 查询所有机构
     *
     * @return {@link List< Organization >} 机构列表
     * @author nza
     * @createTime 2021/3/3 9:45
     */
    public List<Organization> findAllOrganization() {
        return (List<Organization>) organizationRepository.findAll();
    }
}