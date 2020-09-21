package com.mxz.common.mq.props;

import lombok.Data;

/**
 * 生产者配置
 * @Author mxz
 * @Date 2020/9/20 1:17
 **/
@Data
public class MQProducerProp {
    /**
     * 消息队列配置
     */
    private RocketMq rocketmq = new RocketMq();

    /**
     * beanName
     */
    private String beanName ;
}
