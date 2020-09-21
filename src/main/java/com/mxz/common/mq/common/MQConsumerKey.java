
package com.mxz.common.mq.common;

import lombok.Data;

import java.util.Objects;

/**
 * 消息队列handler的Key
 *
 * @author mxz on 2020/09/21 15:07
 */
public class MQConsumerKey {

    /**
     * topic
     */
    private String topic;
    /**
     * tag
     */
    private String tag;
    /**
     * consumerId
     */
    private String consumerId;

    public MQConsumerKey(String topic, String tag, String consumerId) {
        this.topic = topic;
        this.tag = tag;
        this.consumerId = consumerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MQConsumerKey that = (MQConsumerKey) o;
        return Objects.equals(topic, that.topic) &&
                Objects.equals(tag, that.tag) &&
                Objects.equals(consumerId, that.consumerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, tag, consumerId);
    }
}
