
package com.mxz.common.mq.listener;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.mxz.common.mq.common.MQConsumerKey;
import com.mxz.common.mq.common.MQMessageBean;
import com.mxz.common.mq.common.RocketMqAction;
import com.mxz.common.mq.handler.MQHandler;
import com.mxz.common.mq.props.MQConsumerProp;
import com.mxz.common.mq.util.MQHandlerHolder;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 监听器统一处理入口
 *
 * @author mxz on 2020/09/21 14:25
 */
@Slf4j
public class RocketMqListener implements MQMessageListener {

    /**
     * 保存消息队列属性
     */
    private MQConsumerProp consumerProp = null;

    public RocketMqListener(MQConsumerProp consumerProp) {
        this.consumerProp = consumerProp;
    }

    /**
     * 处理消息， 再根据tag 进行过滤
     * @param message 消息
     * @param concurrentlyContext
     * @return
     */
    @Override
    public RocketMqAction consume(MQMessageBean message, ConsumeConcurrentlyContext concurrentlyContext) {
        try {
            Properties prop = consumerProp.getRocketmq().getProperties();
            String topic = prop.getProperty("topic");
            String consumerId = prop.getProperty("ConsumerId");
            MQConsumerKey mqConsumerKey = new MQConsumerKey(topic, message.getTag(), consumerId);
            MQHandler handler = MQHandlerHolder.getHandler(mqConsumerKey);
            if (handler != null) {
                // 处理消息
                String messageBody = new String(message.getBody(), "utf-8");
                return handler.consume(mqConsumerKey, messageBody);
            }
            log.error("消息tag没有对应的handler!" + message.toString());
            return RocketMqAction.CommitMessage;
        } catch (UnsupportedEncodingException e) {
            log.error("消息消费失败：message=" + message);
            log.debug("消息消费失败：message=" + message, e);
            return RocketMqAction.ReconsumeLater;
        }
    }
}
