
package com.mxz.common.mq.handler;

import com.alibaba.fastjson.JSONObject;
import com.mxz.common.mq.common.MQConsumerKey;
import com.mxz.common.mq.common.MQMessageBean;
import com.mxz.common.mq.common.RocketMqAction;
import com.mxz.common.mq.util.MQUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 15:25
 */
@Slf4j
public abstract class MQAutoHandler<T> extends MQHandler {
    @Override
    protected RocketMqAction handleMessage(MQConsumerKey mqConsumerKey, String messageBody) {
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if (t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType) t).getActualTypeArguments();
            entityClass = (Class<T>) p[0];
        }
        T eventDTO = MQUtils.from(messageBody, entityClass);
        log.debug("hand message ：" + messageBody);
        return this.handleMessage(mqConsumerKey, eventDTO, null);
    }

    /**
     * 消费者处理方法
     *
     */
    public abstract RocketMqAction handleMessage(MQConsumerKey consumerKey, T eventDTO, MQMessageBean message);
}
