
package com.mxz.common.mq.proxy;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.SendStatus;
import com.mxz.common.mq.common.MQException;
import com.mxz.common.mq.common.MQMessageBean;
import com.mxz.common.mq.common.MQProducerImpl;
import com.mxz.common.mq.common.MQSenderStatus;
import lombok.Data;

import java.util.Properties;

/**
 * mq代理
 *
 * @author mxz on 2020/09/21 10:20
 */
@Data
public class MQProducerProxy {
    private Properties prop;
    private MQProducerImpl producer;
    public void start() {
        try {
            producer = new MQProducerImpl(prop);
            producer.start();
        } catch (MQException e) {
        }
    }

    public void shutdown() {
        if (producer == null) {
            return;
        }
        try {
            producer.shutdown();
        } catch (MQException e) {
        }
    }

    public MQSenderStatus send(MQMessageBean messageBean) {
        try {
            SendResult sendResult = producer.send(messageBean);
            if(sendResult == null || !SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
                return MQSenderStatus.FAILURE;
            }
            return MQSenderStatus.SUCCESS;
        } catch (MQException e) {

        }
        return MQSenderStatus.FAILURE;
    }
}
