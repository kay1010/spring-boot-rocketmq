package com.fk.rocketmq.config.queueSelector;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义Queue选择器 根据指定参数对topic下的Queue数量取模确定参数对应的唯一的Queue，可以用于实现顺序消息
 */
@Component
public class MySelectorMessageQueue implements MessageQueueSelector {
    private static Logger log = LoggerFactory.getLogger(MySelectorMessageQueue.class);
    @Override
    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
        log.info("使用MySelectorMessageQueue 选择Queue 发送顺序消息");
        int queueNum = Integer.valueOf(String.valueOf(o)) % list.size();
        return list.get(queueNum);
    }
}
