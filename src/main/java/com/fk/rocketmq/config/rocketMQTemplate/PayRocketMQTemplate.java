package com.fk.rocketmq.config.rocketMQTemplate;

import com.fk.rocketmq.common.RocketmqConstant;
import org.apache.rocketmq.spring.annotation.ExtRocketMQTemplateConfiguration;
import org.apache.rocketmq.spring.core.RocketMQTemplate;

/**
 * 为支付事务消息自定义的RocketMQTemplate实例
 */
@ExtRocketMQTemplateConfiguration(nameServer = "${rocketmq.name-server:}",group = RocketmqConstant.PAY_PRODUCER_GROUP)
public class PayRocketMQTemplate extends RocketMQTemplate {
}
