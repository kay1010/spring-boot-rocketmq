package com.fk.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.fk.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 消费者：自定义的PaySuccessTransactionListner的PayRocketMQTemplate实例发送的事务消息
 */
@Component
@RocketMQMessageListener(topic = RocketmqConstant.TOPIC_PAYSUCESS, consumerGroup = RocketmqConstant.CONSUMER_GROUP_TEST, messageModel = MessageModel.CLUSTERING)
public class PaySuccessConsumerListener implements RocketMQListener<String> {
    private Logger log = LoggerFactory.getLogger(PaySuccessConsumerListener.class);
    @Override
    public void onMessage(String msg) {
        log.info(Thread.currentThread().getName()+" -> 由自定义PayRocketMQTemplate发送的事务消息接收："+JSON.toJSONString(msg, true));
    }

}
