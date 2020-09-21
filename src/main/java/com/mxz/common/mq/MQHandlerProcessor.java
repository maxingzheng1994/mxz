
package com.mxz.common.mq;

import com.google.common.collect.Sets;
import com.mxz.common.mq.handler.MQHandler;
import com.mxz.common.mq.props.MQConfigProp;
import com.mxz.common.mq.props.MQConsumerProp;
import com.mxz.common.mq.util.MQHandlerHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 消息处理器
 *
 * @author mxz on 2020/09/21 15:35
 */
@Component
@Slf4j
public class MQHandlerProcessor implements BeanPostProcessor {

    @Autowired
    private MQConfigProp mqConfigProp;

    /**
     * 将消息handler 全部交由 {@link MQHandlerHolder} 管理
     * @param bean 对象
     * @param beanName bean名称
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MQHandler) {
            Class<?> beanClass = bean.getClass();
            String className = beanClass.getName();
            List<MQHandlerHolder.HandlerClassVO> handlerClassList = MQHandlerHolder.getHandlerClassByClassName(className);
            for (MQHandlerHolder.HandlerClassVO handlerClassVO : handlerClassList) {
                    MQHandlerHolder.addHandler(handlerClassVO.getMqConsumerKey(),(MQHandler) bean);
                    log.info("添加消息处理器 {} {}", handlerClassVO.getMqConsumerKey(), bean);
            }
        }
        return bean;
    }

    @PostConstruct
    public void init() {
        initMQHandlerMap();
    }

    private void initMQHandlerMap() {
        Map<String, MQConsumerProp> consumers = mqConfigProp.getConsumer();

        for (String key : consumers.keySet()) {
            MQConsumerProp mqConsumerProp = consumers.get(key);
            Properties prop = mqConsumerProp.getRocketmq().getProperties();
            String topic = prop.getProperty("topic");
            String consumerId = prop.getProperty("ConsumerId");
            // 处理类
            Map<String, String> tagHandlerClassMap = mqConsumerProp.getTagHandlerClass();
            // 初始化tag对应的handlerClass，并保存起来
            for (String tag : tagHandlerClassMap.keySet()) {
                HashSet<String> tagHandlerClass = Sets.newHashSet(tagHandlerClassMap.get(tag).split("\\|"));
                for (String handlerClass : tagHandlerClass) {
                    MQHandlerHolder.addHandlerClass(topic, tag, consumerId, handlerClass);
                }
            }
        }

    }
}
