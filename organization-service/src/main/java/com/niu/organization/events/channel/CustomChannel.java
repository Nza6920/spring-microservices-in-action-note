package com.niu.organization.events.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义隧道
 *
 * @author [nza]
 * @version 1.0 [2021/03/17 16:12]
 * @createTime [2021/03/17 16:12]
 */
public interface CustomChannel {

    /**
     * 隧道名称
     */
    String NAME = "outputBound";

    /**
     * 自定义出隧道
     *
     * @return {@link MessageChannel}
     */
    @Output("outputBound")
    MessageChannel output();
}
