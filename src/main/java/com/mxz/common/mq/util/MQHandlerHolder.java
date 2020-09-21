
package com.mxz.common.mq.util;

import com.mxz.common.mq.common.MQConsumerKey;
import com.mxz.common.mq.handler.MQHandler;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 处理消息持有者
 *
 * @author mxz on 2020/09/21 14:27
 */
public class MQHandlerHolder {

    /**
     * MQConsumerKey和IMQHandler的映射
     */
    private static Map<MQConsumerKey, MQHandler> handlerMap = new HashMap<>();

    private static List<HandlerClassVO> handlerClassList = new ArrayList<HandlerClassVO>();
    /**
     * 获取处理方法
     *
     * @param tag
     */
    public static MQHandler getHandler(String topic, String tag, String consumerId) {
        return handlerMap.get(new MQConsumerKey(topic, tag, consumerId));
    }

    public static MQHandler getHandler(MQConsumerKey mqConsumerKey) {
        return handlerMap.get(mqConsumerKey);
    }

    public static void addHandlerClass(String topic, String tag, String consumerId, String tagHandlerClass) {
        handlerClassList.add(new HandlerClassVO(tagHandlerClass, new MQConsumerKey(topic, tag, consumerId)));
    }

    public static List<HandlerClassVO> getHandlerClassByClassName(String className) {
        return handlerClassList.stream().filter(item -> item.getClassName().equals(className)).collect(Collectors.toList());
    }

    public static void addHandler(MQConsumerKey mqConsumerKey, MQHandler bean) {
        handlerMap.put(mqConsumerKey, bean);
    }

    /**
     * 处理类
     */
    @Data
    @AllArgsConstructor
    public static class HandlerClassVO {
        String className;
        MQConsumerKey mqConsumerKey;
    }
}
