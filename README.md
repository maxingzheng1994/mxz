# mxz
This is my new project




# mq配置

生产者配置
ProducerId
namesrvAddr
isVipChannelEnabled
SendMsgTimeoutMillis

消费者配置

ConsumerId
maxReconsumeTimes
consumeTimeout
isVipChannelEnabled
MessageModel
NAMESRV_ADDR
ConsumeThreadNums


1. 创建 生产者消费者 启动
setSubscriptionTable 消费者监听多个topic 用topic 和expression 过滤



消费的配置统一在配置文件中



消费者名称
com.mxz.mq.consumer.p1.rocketmq.properties.ConsumerId=cc234
com.mxz.mq.consumer.p1.rocketmq.properties.topic=topicA
#com.mxz.mq.consumer.p1.rocketmq.properties.expression=
com.mxz.mq.consumer.p1.rocketmq.properties.NAMESRV_ADDR=127.0.0.1:9876

com.mxz.mq.consumer.p1.tag-handler-class.tag1=com.mxz.common.mq.handler.TestHandler|com.mxz.common.mq.handler.TestHandler