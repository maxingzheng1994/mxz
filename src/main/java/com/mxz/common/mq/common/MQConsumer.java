
package com.mxz.common.mq.common;

import com.mxz.common.mq.listener.MQMessageListener;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 11:12
 */
public interface MQConsumer extends MQAdmin {
    void start() throws MQException;

    void shutdown() throws MQException;

    void subscribe(String var1, String var2, MQMessageListener var3) throws MQException;

    void unsubscribe(String var1) throws MQException;
}
