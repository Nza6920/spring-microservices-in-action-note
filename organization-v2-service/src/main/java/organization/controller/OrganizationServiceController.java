package organization.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import organization.model.Organization;
import organization.pojo.ServerResponse;
import organization.service.OrganizationService;

import java.util.List;

/**
 * 机构控制器
 *
 * @author [nza]
 * @version 1.0 [2021/03/03 17:16]
 * @createTime [2021/03/03 17:16]
 */
@RestController
@RequestMapping(value = "v2/organizations")
@AllArgsConstructor
public class OrganizationServiceController {

    private final OrganizationService organizationService;

    /**
     * 根据ID获取机构
     *
     * @param organizationId 机构ID
     * @return {@link ServerResponse < Organization >}
     * @author nza
     * @createTime 2021/3/3 17:21
     */
    @GetMapping("/{organizationId}")
    public ServerResponse<Organization> getOrganization(@PathVariable("organizationId") String organizationId) {
        return ServerResponse.createBySuccess(organizationService.findById(organizationId));
    }

    /**
     * 新增机构
     *
     * @param org 机构实体
     * @return {@link ServerResponse < Organization >}
     * @author nza
     * @createTime 2021/3/3 17:21
     */
    @PostMapping()
    public ServerResponse<Object> saveOrganization(@RequestBody Organization org) {
        organizationService.saveOrganization(org);
        return ServerResponse.createBySuccess();
    }

    /**
     * 新增机构
     *
     * @return {@link ServerResponse < Organization >}
     * @author nza
     * @createTime 2021/3/3 17:21
     */
    @GetMapping()
    public ServerResponse<List<Organization>> findAllOrganization() {
        return ServerResponse.createBySuccess(organizationService.findAllOrganization());
    }
}
