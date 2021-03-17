package com.niu.organization.service;

import cn.hutool.core.util.IdUtil;
import com.niu.organization.enums.MessageTypeEnums;
import com.niu.organization.events.source.SimpleSourceBean;
import com.niu.organization.model.Organization;
import com.niu.organization.repository.OrganizationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final SimpleSourceBean simpleSourceBean;

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

        // 发布消息 通知消费者数据发生了变化
        simpleSourceBean.publishOrgChange(MessageTypeEnums.SAVE.getValue(), organization.getId());

        return newOrg.getId();
    }

    /**
     * 删除机构
     *
     * @param organizationId 机构ID
     * @author nza
     * @createTime 2021/3/3 9:43
     */
    public void deleteOrganization(String organizationId) {
        organizationRepository.deleteById(organizationId);

        // 发布消息 通知消费者数据发生了变化
        simpleSourceBean.publishOrgChange(MessageTypeEnums.DELETE.getValue(), organizationId);
    }

    /**
     * 根据ID获取机构
     *
     * @param id 机构ID
     * @return {@link com.niu.organization.model.Organization}
     * @createTime 2021/3/3 17:20
     */
    public Organization findById(String id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElse(null);
    }

    /**
     * 查询所有机构
     *
     * @return {@link List<com.niu.organization.model.Organization>} 机构列表
     * @author nza
     * @createTime 2021/3/3 9:45
     */
    public List<Organization> findAllOrganization() {
        return (List<Organization>) organizationRepository.findAll();
    }
}
