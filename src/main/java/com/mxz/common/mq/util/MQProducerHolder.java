
package com.mxz.common.mq.util;

import com.mxz.common.mq.proxy.MQProducerProxy;

import java.util.HashMap;
import java.util.Map;

/**
 * mq生产者持有者
 *
 * @author mxz on 2020/09/21 10:12
 */
public class MQProducerHolder {
    /**
     * 记录生产者
     */
    private static Map<String, MQProducerProxy> producerMap = new HashMap<String, MQProducerProxy>();


    public static void addMQProducer(String beanName, MQProducerProxy mqProducer) {
        producerMap.put(beanName, mqProducer);
    }

    public static MQProducerProxy getMqProducer(String beanName) {
        return producerMap.get(beanName);
    }

    public static Map<String, MQProducerProxy> getAllProducer() {
        return producerMap;
    }
}
