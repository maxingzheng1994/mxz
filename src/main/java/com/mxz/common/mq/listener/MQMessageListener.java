
package com.mxz.common.mq.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.mxz.common.mq.common.MQMessageBean;
import com.mxz.common.mq.common.RocketMqAction;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 11:13
 */
public interface MQMessageListener {

    RocketMqAction consume(MQMessageBean messageBean, ConsumeConcurrentlyContext concurrentlyContext);
}
