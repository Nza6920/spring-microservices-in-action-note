package com.niu.licenses.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.niu.licenses.model.Organization;
import com.niu.licenses.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
     * @return {@link java.lang.String} 新增的机构ID
     * @author nza
     * @createTime 2021/3/3 9:43
     */
    public String saveOrganization(Organization organization) {

        organization.setId(IdUtil.simpleUUID());
        Organization newOrg = organizationRepository.save(organization);

        return newOrg.getId();
    }

    /**
     * 查询所有机构
     *
     * @author nza
     * @createTime 2021/3/3 9:45
     * @return    {@link java.util.List<com.niu.licenses.model.Organization>} 机构列表
     */
    public List<Organization> findAllOrganization() {
        return (List<Organization>) organizationRepository.findAll();
    }
}
