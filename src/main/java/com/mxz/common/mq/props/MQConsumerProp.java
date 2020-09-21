
package com.mxz.common.mq.props;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 消费者配置
 *
 * @author mxz on 2020/09/21 10:46
 */
@Data
public class MQConsumerProp {
    /**
     * 消息队列配置
     */
    private RocketMq rocketmq = new RocketMq();

    /**
     * 是否校验重复消费，默认为否
     */
    private String isDistinct = "false";

    /**
     * tag对应的handler class key:tag,value className
     */
    private Map<String, String> tagHandlerClass = new HashMap<String, String>();
}
