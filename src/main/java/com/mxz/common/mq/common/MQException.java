
package com.mxz.common.mq.common;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.UtilAll;
import com.alibaba.rocketmq.common.help.FAQUrl;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 10:27
 */
public class MQException extends RuntimeException{
    public MQException() {
    }

    public MQException(String message) {
        super(message);
    }

    public MQException(String message, Throwable cause) {
        super(message, cause);
    }
}
