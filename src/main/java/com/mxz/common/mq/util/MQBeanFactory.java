package com.mxz.common.mq.util;

import com.mxz.common.mq.props.MQConsumerProp;
import com.mxz.common.mq.proxy.MQConsumerProxy;
import com.mxz.common.mq.proxy.MQProducerProxy;

import java.util.List;
import java.util.Properties;

/**
 * 生产者消费者创建工厂
 * @Author mxz
 * @Date 2020/9/20 1:29
 **/
public class MQBeanFactory {

    public static MQProducerProxy createProducer(Properties properties) {
        MQProducerProxy mqProducerProxy = new MQProducerProxy();
        mqProducerProxy.setProp(properties);
        return mqProducerProxy;
    }

    public static MQConsumerProxy createConsumer(Properties properties, List<MQConsumerProp> mqConsumerProps) {
        MQConsumerProxy mqConsumerProxy = new MQConsumerProxy();
        mqConsumerProxy.setProp(properties);
        // 设置监听器注册表
        mqConsumerProxy.setSubscriptionTable(mqConsumerProps);
        return mqConsumerProxy;
    }
}
