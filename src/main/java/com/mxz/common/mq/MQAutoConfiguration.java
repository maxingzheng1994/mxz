package com.mxz.common.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @Author mxz
 * @Date 2020/9/20 1:13
 **/
@Configuration
@EnableConfigurationProperties({MQConfigProp.class})
@Slf4j
public class MQAutoConfiguration {

    private MQConfigProp mqConfigProp;

    @PostConstruct
    public void init() {
        startProducer();
    }

    /**
     * 启动生产者
     */
    private void startProducer() {
        Map<String, MQProducer> producer = mqConfigProp.getProducer();
        // 根据配置创建生产者

    }

    private void destroyProducer() {
        for (Map.Entry<String, IMQProducer> producer : MQProducerHolder.getAllProducer().entrySet()) {
            if (producer.getValue() != null) {
                producer.getValue().shutdown();
            }
        }
    }
}
