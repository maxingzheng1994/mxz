
package com.mxz.common.mq.proxy;

import com.mxz.common.mq.common.*;
import com.mxz.common.mq.listener.MQMessageListener;
import com.mxz.common.mq.listener.RocketMqListener;
import com.mxz.common.mq.props.MQConsumerProp;
import com.mxz.common.mq.props.Subscription;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * mq代理
 *
 * @author mxz on 2020/09/21 10:20
 */
@Data
public class MQConsumerProxy {
    private Properties prop;
    private MQConsumerImpl consumer;
    private Map<Subscription, MQMessageListener> subscriptionTable;

    public void start() {
        try {
            consumer = new MQConsumerImpl(prop);
            // 注册监听器
            for (Subscription subscription : subscriptionTable.keySet()) {
                MQMessageListener mqMessageListener = subscriptionTable.get(subscription);
                consumer.subscribe(subscription.getTopic(), subscription.getExpression(), mqMessageListener);
            }
            consumer.start();
        } catch (MQException e) {
        }
    }

    public void shutdown() {
        if (consumer == null) {
            return;
        }
        try {
            consumer.shutdown();
        } catch (MQException e) {
        }
    }

    /**
     * 设置注册表 注册表 《(topic.expression), lisenter》
     * @param mqConsumerProps
     */
    public void setSubscriptionTable(List<MQConsumerProp> mqConsumerProps) {
        subscriptionTable = new HashMap<Subscription, MQMessageListener>();
        for(MQConsumerProp consumerProp : mqConsumerProps) {
            Subscription subs = new Subscription();
            subs.setTopic(consumerProp.getRocketmq().getProperties().getProperty("topic"));
            subs.setExpression(consumerProp.getRocketmq().getProperties().getProperty("expression"));
            RocketMqListener mQListener = new RocketMqListener(consumerProp);
            subscriptionTable.put(subs, mQListener);
        }
    }
}
