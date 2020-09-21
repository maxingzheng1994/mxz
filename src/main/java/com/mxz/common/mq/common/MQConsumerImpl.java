
package com.mxz.common.mq.common;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.UtilAll;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;
import com.mxz.common.mq.listener.MQMessageListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 11:13
 */
@Slf4j
public class MQConsumerImpl implements MQConsumer {

    private DefaultMQPushConsumer defaultMQPushConsumer;
    private final ConcurrentHashMap<String, MQMessageListener> subscribeTable = new ConcurrentHashMap<>();
    private final AtomicBoolean started = new AtomicBoolean(false);

    public MQConsumerImpl(Properties properties) throws MQException {
        this.defaultMQPushConsumer = new DefaultMQPushConsumer(properties.getProperty("ConsumerId"));
        String consumerGroup = properties.getProperty("ConsumerId");
        if (null == consumerGroup) {
            throw new MQException("'ConsumerId' property is null");
        } else {
            String maxReconsumeTimes = properties.getProperty("maxReconsumeTimes");
            if (!UtilAll.isBlank(maxReconsumeTimes)) {
                try {
                    this.defaultMQPushConsumer.setMaxReconsumeTimes(Integer.parseInt(maxReconsumeTimes));
                } catch (NumberFormatException var8) {
                }
            }

            String consumeTimeout = properties.getProperty("consumeTimeout");
            if (!UtilAll.isBlank(consumeTimeout)) {
                try {
                    this.defaultMQPushConsumer.setConsumeTimeout((long)Integer.parseInt(consumeTimeout));
                } catch (NumberFormatException var7) {
                }
            }

            boolean isVipChannelEnabled = Boolean.parseBoolean(properties.getProperty("isVipChannelEnabled", "false"));
            this.defaultMQPushConsumer.setVipChannelEnabled(isVipChannelEnabled);
            String messageModel = properties.getProperty("MessageModel", "CLUSTERING");
            this.defaultMQPushConsumer.setMessageModel(MessageModel.valueOf(messageModel));
            this.defaultMQPushConsumer.setConsumerGroup(consumerGroup);
            this.defaultMQPushConsumer.setNamesrvAddr(properties.getProperty("NAMESRV_ADDR"));
            this.defaultMQPushConsumer.setInstanceName(this.buildIntanceName());
            if (properties.containsKey("ConsumeThreadNums")) {
                this.defaultMQPushConsumer.setConsumeThreadMin(Integer.valueOf(properties.get("ConsumeThreadNums").toString()));
                this.defaultMQPushConsumer.setConsumeThreadMax(Integer.valueOf(properties.get("ConsumeThreadNums").toString()));
            } else {
                this.defaultMQPushConsumer.setConsumeThreadMin(10);
                this.defaultMQPushConsumer.setConsumeThreadMax(10);
            }

        }
    }

    @Override
    public void shutdown() throws MQException {
        if (this.started.compareAndSet(true, false)) {
            this.defaultMQPushConsumer.shutdown();
        }
    }

    @Override
    public void subscribe(String topic, String subExpression, MQMessageListener listener) throws MQException {
        if (null == topic) {
            throw new MQException("topic is null");
        } else if (null == listener) {
            throw new MQException("listener is null");
        } else {
            try {
                this.subscribeTable.put(topic, listener);
                this.defaultMQPushConsumer.subscribe(topic, subExpression);
            } catch (MQClientException e) {
                throw new MQException("defaultMQPushConsumer subscribe exception", e);
            }
        }
    }

    @Override
    public void unsubscribe(String topic) throws MQException {
        if (null != topic) {
            this.defaultMQPushConsumer.unsubscribe(topic);
        }
    }
    @Override
    public void start() throws MQException {
        this.defaultMQPushConsumer.registerMessageListener(new MQConsumerImpl.MessageListenerImpl());
        try {
            if (this.started.compareAndSet(false, true)) {
                this.defaultMQPushConsumer.start();
            }
        } catch (Exception var2) {
            throw new MQException(var2.getMessage());
        }
    }

    protected String buildIntanceName() {
        return Integer.toString(UtilAll.getPid()) + "#" + this.defaultMQPushConsumer.getNamesrvAddr().hashCode() + "#" + System.nanoTime();
    }


    public boolean isStarted() {
        return this.started.get();
    }

    public boolean isClosed() {
        return !this.isStarted();
    }


    /**
     * 消息处理总入口
     */
    class MessageListenerImpl implements MessageListenerConcurrently {
        MessageListenerImpl() {
        }

        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
            MessageExt msgRMQ = (MessageExt)msgs.get(0);
            MQMessageBean msg = new MQMessageBean();
            if (msgRMQ.getTopic() != null) {
                msg.setTopic(msgRMQ.getTopic());
            }

            if (msgRMQ.getKeys() != null) {
                msg.setKey(msgRMQ.getKeys());
            }

            if (msgRMQ.getTags() != null) {
                msg.setTag(msgRMQ.getTags());
            }

            if (msgRMQ.getBody() != null) {
                msg.setBody(msgRMQ.getBody());
            }

            msg.setReconsumeTimes(msgRMQ.getReconsumeTimes());
            Map<String, String> stringStringMap = msgRMQ.getProperties();
            msg.setMsgID(msgRMQ.getMsgId());
            if (stringStringMap != null && stringStringMap.get("__transactionId__") != null) {
                msg.setMsgID((String)stringStringMap.get("__transactionId__"));
            }

            MQMessageListener listener = (MQMessageListener)MQConsumerImpl.this.subscribeTable.get(msg.getTopic());
            RocketMqAction action = listener.consume(msg, context);
            if (action != null) {
                switch(action) {
                    case CommitMessage:
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    case ReconsumeLater:
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
            }
            return null;
        }
    }
}
