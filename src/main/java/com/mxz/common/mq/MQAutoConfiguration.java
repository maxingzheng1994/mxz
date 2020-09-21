package com.mxz.common.mq;

import com.google.common.collect.Lists;
import com.mxz.common.mq.props.MQConfigProp;
import com.mxz.common.mq.props.MQConsumerProp;
import com.mxz.common.mq.props.MQProducerProp;
import com.mxz.common.mq.proxy.MQConsumerProxy;
import com.mxz.common.mq.proxy.MQProducerProxy;
import com.mxz.common.mq.util.MQBeanFactory;
import com.mxz.common.mq.util.MQConsumerHolder;
import com.mxz.common.mq.util.MQProducerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Author mxz
 * @Date 2020/9/20 1:13
 **/
@Configuration
@EnableConfigurationProperties({MQConfigProp.class})
@Slf4j
public class MQAutoConfiguration {

    @Autowired
    private MQConfigProp mqConfigProp;

    @PostConstruct
    public void init() {
        startProducer();
        startConsumer();
    }

    /**
     * 启动消费者
     */
    private void startConsumer() {
        Map<String, List<MQConsumerProp>> allConsumer = new HashMap<String, List<MQConsumerProp>>();
        Map<String, MQConsumerProp> consumerPropMap = mqConfigProp.getConsumer();
        // 按照通道不同的消费名称跟通道进行归类  将同一consumerId 放在一起
        for (String key : consumerPropMap.keySet()) {
            MQConsumerProp consumerProp = consumerPropMap.get(key);
            Properties prop = consumerProp.getRocketmq().getProperties();
            String consumerId = prop.getProperty("ConsumerId");
            List<MQConsumerProp> consumerProps = allConsumer.getOrDefault(consumerId, Lists.newArrayList());
            consumerProps.add(consumerProp);
            allConsumer.put(consumerId, consumerProps);
        }

        for (String key : allConsumer.keySet()) {
            List<MQConsumerProp> mqConsumerProps = allConsumer.get(key);
            MQConsumerProxy consumer = MQBeanFactory.createConsumer(mqConsumerProps.get(0).getRocketmq().getProperties(), mqConsumerProps);
            consumer.start();
            log.info("启动消费者 {}, consumer: {}", key, consumer.toString());
            MQConsumerHolder.addConsumer(key, consumer);
        }

    }

    /**
     * 启动生产者
     */
    private void startProducer() {
        // 根据配置创建生产者
        Map<String, MQProducerProp> producerPropMap = mqConfigProp.getProducer();
        for (String key : producerPropMap.keySet()) {
            MQProducerProp prop = producerPropMap.get(key);
            MQProducerProxy producer = MQBeanFactory.createProducer(prop.getRocketmq().getProperties());
            producer.start();
            log.info("启动消息生产者 {}, producer: {}", prop.getBeanName(), producer.toString());
            MQProducerHolder.addMQProducer(prop.getBeanName(), producer);
        }
    }

    @Component
    public static class AutoDisposableBean implements DisposableBean, ExitCodeGenerator {

        @Override
        public void destroy() throws Exception {
            destroyProducer();
            destroyConsumer();
        }

        private void destroyProducer() {
            for (Map.Entry<String, MQProducerProxy> producer : MQProducerHolder.getAllProducer().entrySet()) {
                if (producer.getValue() != null) {
                    producer.getValue().shutdown();
                }
            }
        }

        private void destroyConsumer() {
            // 将所有消息队列监听关闭
            MQConsumerHolder.getAllConsumer().entrySet().stream().forEach(item -> item.getValue().shutdown());
        }

        @Override
        public int getExitCode() {
            return 5;
        }
    }

}


