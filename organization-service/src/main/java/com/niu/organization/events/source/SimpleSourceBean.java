package com.niu.organization.events.source;

import com.niu.organization.events.model.OrganizationChangeModel;
import com.niu.organization.utils.UserContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 消息代理Bean
 *
 * @author [nza]
 * @version 1.0 [2021/03/15 17:05]
 * @createTime [2021/03/15 17:05]
 */
@Component
@Slf4j
@AllArgsConstructor
public class SimpleSourceBean {

    private final Source source;

    /**
     * 发送消息
     *
     * @param action 动作
     * @param orgId  机构ID
     * @author nza
     * @createTime 2021/3/15 17:26
     */
    public void publishOrgChange(String action, String orgId) {
        log.debug("发送 kafka 消息: {}, 机构ID: {}", action, orgId);

        // 构建消息载荷
        OrganizationChangeModel organizationChangeModel = new OrganizationChangeModel(OrganizationChangeModel.class.getName(),
                action,
                orgId,
                UserContextHolder.getContext().getCorrelationId());

        // 发布消息
        source.output()
                .send(MessageBuilder.withPayload(organizationChangeModel)
                        .build());
    }
}
