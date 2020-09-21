
package com.mxz.common.mq.common;

import com.alibaba.rocketmq.client.producer.SendResult;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 10:27
 */
public interface MQProducer extends MQAdmin {
    void start() throws MQException;

    void shutdown() throws MQException;

    SendResult send(MQMessageBean var1) throws MQException;
}
