
package com.mxz.common.mq.util;

import com.mxz.common.mq.proxy.MQConsumerProxy;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息消费者持有者
 *
 * @author mxz on 2020/09/21 11:26
 */
@Data
public class MQConsumerHolder {
    /**
     * key 消费者id
     */
    private static Map<String, MQConsumerProxy> map = new HashMap<>();

    /**
     * 增加消费者
     *
     * @param consumerId
     * @param consumerBean
     */
    public static void addConsumer(String consumerId, MQConsumerProxy consumerBean) {
        map.put(consumerId, consumerBean);
    }

    /**
     * 取得一个消费者
     *
     * @param consumerId
     */
    public static MQConsumerProxy getConsumer(String consumerId) {
        return map.get(consumerId);
    }

    /**
     * 取的所有消费者
     *
     */
    public static Map<String, MQConsumerProxy> getAllConsumer() {
        return map;
    }
}
