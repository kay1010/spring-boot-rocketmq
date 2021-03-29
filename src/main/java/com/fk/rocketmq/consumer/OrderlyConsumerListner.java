package com.fk.rocketmq.consumer;

import com.fk.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 顺序消息消费者 consumeMode = ConsumeMode.ORDERLY
 */
@Component
@RocketMQMessageListener(nameServer = "${rocketmq.name-server:}",topic = RocketmqConstant.TOPIC_ORDERLY, consumerGroup = RocketmqConstant.CONSUMER_ORDER_TEST, consumeMode = ConsumeMode.ORDERLY)
public class OrderlyConsumerListner implements RocketMQListener<String> {
    private Logger log = LoggerFactory.getLogger(OrderlyConsumerListner.class);
    @Override
    public void onMessage(String message) {
        try {
            Thread.sleep(2000);
            log.info(Thread.currentThread().getName() + "顺序消费接收: " + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
