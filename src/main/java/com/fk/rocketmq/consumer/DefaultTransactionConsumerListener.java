package com.fk.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.fk.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消费者：默认的RocketMQTemplate实例发送的事务消息
 * */
@Component
@RocketMQMessageListener(consumeThreadMax=5,topic = RocketmqConstant.TOPIC_TRANSACTION, consumerGroup = RocketmqConstant.CONSUMER_TRANSACTION_TEST,messageModel = MessageModel.CLUSTERING)
public class DefaultTransactionConsumerListener implements RocketMQListener<String> {
    private Logger log = LoggerFactory.getLogger(DefaultTransactionConsumerListener.class);
    @Override
    public void onMessage(String message) {
        try {
            Thread.sleep(10000);
            log.info(Thread.currentThread().getName()+" -> 默认的RocketMQTemplate发送的事务消费接收："+ JSON.toJSONString(message, true));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
