
package com.mxz.common.mq.handler;

import com.mxz.common.mq.common.MQConsumerKey;
import com.mxz.common.mq.common.RocketMqAction;

/**
 * 消息处理器
 *
 * @author mxz on 2020/09/21 14:37
 */
public abstract class MQHandler {
    
    public RocketMqAction consume(MQConsumerKey mqConsumerKey, String messageBody) {
        return handleMessage(mqConsumerKey, messageBody);
    }

    protected abstract RocketMqAction handleMessage(MQConsumerKey mqConsumerKey, String messageBody);
}
