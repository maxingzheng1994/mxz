package com.mxz.common.mq.example;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.impl.producer.DefaultMQProducerImpl;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendCallback;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.UnsupportedEncodingException;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/16 17:44
 */
@Component
@Slf4j
public class MQProducer {
    @Value("${spring.rocketmq.namesrvAddr}")
    private String nameServer;

    public final DefaultMQProducer producer = new DefaultMQProducer("TestProducer");

    @PostConstruct
    public void start() {
        try {
            producer.setNamesrvAddr(nameServer);
            producer.start();
        } catch (MQClientException e) {
            log.error(e.getErrorMessage());
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String data, String topic, String tags, String keys) {
        try {
            byte[] messageBody = data.getBytes(RemotingHelper.DEFAULT_CHARSET);

            Message message = new Message(topic, tags, keys, messageBody);

            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    log.info("Mq 发送成功", sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    log.error(throwable.getMessage(), throwable);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    @PreDestroy
    public void stop() {
        if (producer != null) {
            producer.shutdown();
            log.info("生产者关闭");
        }
    }
}
