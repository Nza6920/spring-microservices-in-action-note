package com.niu.organization.events.source;

import com.niu.organization.events.channel.CustomChannel;
import com.niu.organization.events.model.OrganizationChangeModel;
import com.niu.organization.utils.UserContextHolder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 消息代理Bean
 *
 * @author [nza]
 * @version 1.0 [2021/03/15 17:05]
 * @createTime [2021/03/15 17:05]
 */
@Slf4j
@AllArgsConstructor
@EnableBinding({CustomChannel.class})
public class SimpleSourceBean {

    private final CustomChannel customChannel;

    /**
     * 发送消息
     *
     * @param action 动作
     * @param orgId  机构ID
     * @author nza
     * @createTime 2021/3/15 17:26
     */
    public void publishOrgChange(String action, String orgId) {
        log.debug("发送消息: {}, 机构ID: {}", action, orgId);

        // 构建消息载荷
        OrganizationChangeModel organizationChangeModel = new OrganizationChangeModel(OrganizationChangeModel.class.getName(),
                action,
                orgId,
                UserContextHolder.getContext().getCorrelationId());

        // 发布消息
        customChannel.output()
                .send(MessageBuilder.withPayload(organizationChangeModel)
                        .build());
    }
}
