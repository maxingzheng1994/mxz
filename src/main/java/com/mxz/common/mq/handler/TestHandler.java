
package com.mxz.common.mq.handler;

import com.mxz.common.mq.common.MQConsumerKey;
import com.mxz.common.mq.common.MQMessageBean;
import com.mxz.common.mq.common.RocketMqAction;
import com.mxz.service.article.model.Article;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author mxz on 2020/09/21 15:33
 */
@Component
public class TestHandler extends MQAutoHandler<Article> {
    @Override
    public RocketMqAction handleMessage(MQConsumerKey consumerKey, Article eventDTO, MQMessageBean message) {
        System.out.println("handler success" + eventDTO.toString());
        return RocketMqAction.CommitMessage;
    }
}
