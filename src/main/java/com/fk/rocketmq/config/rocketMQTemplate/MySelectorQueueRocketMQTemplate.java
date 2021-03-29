package com.fk.rocketmq.config.rocketMQTemplate;

import com.fk.rocketmq.common.RocketmqConstant;
import com.fk.rocketmq.config.queueSelector.MySelectorMessageQueue;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**自定义MessageQueueSelector的RocketMQTemplate实例Bean*/
@ExtRocketMQTemplateConfiguration(nameServer = "${rocketmq.name-server:}",group = RocketmqConstant.PRODUCER_GROUP_ORDERLY)
public class MySelectorQueueRocketMQTemplate extends RocketMQTemplate implements InitializingBean {
    @Autowired
    private MySelectorMessageQueue mySelectorMessageQueue;

    /** 设置自定义的 MessageQueueSelector*/
    @Override
    public void afterPropertiesSet() throws Exception {
        setMessageQueueSelector(mySelectorMessageQueue);
    }
}
