package com.niu.licenses.events.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 自定义通道
 *
 * @version 1.0 [2021/03/17 15:56]
 * @author [nza]
 * @createTime [2021/03/17 15:56]
 */
public interface CustomChannels {

    /**
     * 通道名称
     */
    String NAME = "inboundOrgChanges";

    /**
     * 入通道
     *
     * @return {@link SubscribableChannel}
     */
    @Input("inboundOrgChanges")
    SubscribableChannel orgs();
}
