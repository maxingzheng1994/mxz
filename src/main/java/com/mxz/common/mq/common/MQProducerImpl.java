package com.mxz.common.mq.common;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.UtilAll;
import com.alibaba.rocketmq.common.message.Message;
import com.mxz.common.mq.common.MQAdmin;
import com.mxz.common.mq.common.MQException;
import com.mxz.common.mq.common.MQMessageBean;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author mxz
 * @Date 2020/9/20 1:34
 **/
public class MQProducerImpl implements MQAdmin {
    private DefaultMQProducer defaultMQProducer;
    private final AtomicBoolean started = new AtomicBoolean();

    public MQProducerImpl() {
        super();
    }

    public MQProducerImpl(Properties prop) {
        this.defaultMQProducer = new DefaultMQProducer(prop.getProperty("ProducerId"));
        this.defaultMQProducer.setNamesrvAddr(prop.getProperty("NAMESRV_ADDR"));
        boolean isVipChannelEnabled = Boolean.parseBoolean(prop.getProperty("isVipChannelEnabled", "false"));
        this.defaultMQProducer.setVipChannelEnabled(isVipChannelEnabled);
        if (prop.containsKey("SendMsgTimeoutMillis")) {
            this.defaultMQProducer.setSendMsgTimeout(Integer.valueOf(prop.get("SendMsgTimeoutMillis").toString()));
        } else {
            this.defaultMQProducer.setSendMsgTimeout(5000);
        }

        this.defaultMQProducer.setInstanceName(this.buildIntanceName());
        this.defaultMQProducer.setMaxMessageSize(4194304);
    }

    public void start() throws MQException {
        try {
            if (this.started.compareAndSet(false, true)) {
                this.defaultMQProducer.start();
            }

        } catch (Exception var2) {
            throw new MQException(var2.getMessage(), var2);
        }
    }

    public void shutdown() {
        if (this.started.compareAndSet(true, false)) {
            this.defaultMQProducer.shutdown();
        }

    }

    public SendResult send(MQMessageBean messageBean) throws MQException {
        try {
            Message message = new Message();
            if (messageBean.getTopic() != null) {
                message.setTopic(messageBean.getTopic());
            }

            if (messageBean.getKey() != null) {
                message.setKeys(messageBean.getKey());
            }

            if (messageBean.getTag() != null) {
                message.setTags(messageBean.getTag());
            }

            if (messageBean.getBody() != null) {
                message.setBody(messageBean.getBody());
            }

            message.setDelayTimeLevel(messageBean.getDelayTimeLevel());
            return this.defaultMQProducer.send(message);
        } catch (Exception var3) {
            throw new MQException("[topic:" + messageBean.getTopic() + ", keys:" + messageBean.getKey() + "]", var3);
        }
    }

    @Override
    public boolean isStarted() {
        return this.started.get();
    }

    @Override
    public boolean isClosed() {
        return !this.isStarted();
    }

    protected String buildIntanceName() {
        return Integer.toString(UtilAll.getPid()) + "#" + this.defaultMQProducer.getNamesrvAddr().hashCode() + "#" + System.nanoTime();
    }
}
