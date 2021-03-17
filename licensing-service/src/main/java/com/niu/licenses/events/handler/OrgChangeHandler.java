package com.niu.licenses.events.handler;

import cn.hutool.core.util.StrUtil;
import com.niu.licenses.events.channel.CustomChannels;
import com.niu.licenses.events.model.OrganizationChangeModel;
import com.niu.licenses.repository.OrgRedisRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * 监听机构变化
 *
 * @author [nza]
 * @version 1.0 [2021/03/17 11:55]
 * @createTime [2021/03/17 11:55]
 */
@Slf4j
@EnableBinding(CustomChannels.class)
@AllArgsConstructor
public class OrgChangeHandler {

    private final OrgRedisRepository orgRedisRepository;

    @StreamListener(CustomChannels.NAME)
    public void loggerSink(OrganizationChangeModel model) {
        switch (model.getAction()) {
            case "SAVE":
                log.debug("监听到 SAVE 消息, 机构ID: {}", model.getOrganizationId());
                break;
            case "UPDATE":
                log.debug("监听到 UPDATE 消息, 机构ID: {}", model.getOrganizationId());
                if (StrUtil.isNotEmpty(model.getOrganizationId())) {
                    orgRedisRepository.deleteOrg(model.getOrganizationId());
                }
                break;
            case "DELETE":
                log.debug("监听到 DELETE 消息, 机构ID: {}", model.getOrganizationId());
                if (StrUtil.isNotEmpty(model.getOrganizationId())) {
                    orgRedisRepository.deleteOrg(model.getOrganizationId());
                }
                break;
            default:
                log.error("未知的消息类型: {}", model.getAction());
                break;
        }
    }
}
