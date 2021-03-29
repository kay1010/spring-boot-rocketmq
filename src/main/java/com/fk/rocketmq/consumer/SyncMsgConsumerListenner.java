package com.fk.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.fk.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(nameServer = "${rocketmq.name-server:}",topic = RocketmqConstant.TOPIC_SYNC,consumerGroup = RocketmqConstant.CONSUMER_GROUP_SYNC)
public class SyncMsgConsumerListenner implements RocketMQListener<String> {
    private Logger log = LoggerFactory.getLogger(SyncMsgConsumerListenner.class);
    @Override
    public void onMessage(String msg) {
        log.info(Thread.currentThread().getName()+" -> 同步方式发送的消息 消费者接收："+ JSON.toJSONString(msg, true));
    }
}
