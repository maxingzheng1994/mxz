package com.mxz.common.utils;

import com.mxz.common.mq.MQProducer;

/**
 *
 * @author mxz on 2020/09/16 20:12
 */
public class MQUtils {

    private static MQProducer mqProducer;

    public static void init() {
        if (mqProducer == null) {
            mqProducer = SpringUtil.getBean(MQProducer.class);
        }
    }
    public static void sendMsg(String data, String topic, String tags, String keys) {
        init();
        mqProducer.sendMessage(data, topic, tags, keys);
    }
}
