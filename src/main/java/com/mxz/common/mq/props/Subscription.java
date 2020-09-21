
package com.mxz.common.mq.props;

import lombok.Data;

/**
 * TODO: 注释.
 *
 * @author mxz on 2020/09/21 14:19
 */
@Data
public class Subscription {
    private String topic;
    private String expression;

}
