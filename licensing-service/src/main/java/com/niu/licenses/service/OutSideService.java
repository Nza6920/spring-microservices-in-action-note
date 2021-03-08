package com.niu.licenses.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.niu.licenses.client.OutSideFeignClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 外部服务
 *
 * @author [nza]
 * @version 1.0 [2021/03/08 17:02]
 * @createTime [2021/03/08 17:02]
 */
@Service
@Slf4j
@AllArgsConstructor
public class OutSideService {

    private final OutSideFeignClient outSideFeignClient;

    /**
     * 调用外部服务
     *
     * @author nza
     * @createTime 2021/3/2 21:52
     */
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5")
    },
            threadPoolKey = "testOutSide",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            })
    public Object testOutSide() {
        return outSideFeignClient.testOutSide();
    }
}
