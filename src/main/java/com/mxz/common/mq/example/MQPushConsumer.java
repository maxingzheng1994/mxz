package com.mxz.common.mq.example;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/16 19:57
 */
@Component
@Slf4j
public class MQPushConsumer implements MessageListenerConcurrently {

    @Value("${spring.rocketmq.namesrvAddr}")
    private String nameServer;

    private final DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("TestConsumer");

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        int index = 0;
        try {
            for (; index < msgs.size(); index++) {
                MessageExt msg = msgs.get(index);
                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                log.info("MQ: 消费者接收新信息: {} {} {} {} {}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (index < msgs.size()) {
                context.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PostConstruct
    public void start() {
        try {
            consumer.setNamesrvAddr(nameServer);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.setMessageModel(MessageModel.CLUSTERING);
            consumer.subscribe("TopicTest", "*");
            consumer.registerMessageListener(this);
            consumer.start();
        } catch (MQClientException e) {
            log.error("启动消费者失败：" + e.getErrorMessage());
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
            log.info("消费者关闭");
        }
    }
}
