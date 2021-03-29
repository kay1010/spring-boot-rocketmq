package com.fk.rocketmq.controller;

import com.alibaba.fastjson.JSON;
import com.fk.rocketmq.common.RocketmqConstant;
import com.fk.rocketmq.config.queueSelector.MySelectorMessageQueue;
import com.fk.rocketmq.config.rocketMQTemplate.MySelectorQueueRocketMQTemplate;
import com.fk.rocketmq.config.rocketMQTemplate.PayRocketMQTemplate;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/producer")
public class SendController {
    private static Logger log = LoggerFactory.getLogger(SendController.class);

    @Autowired
    @Qualifier("rocketMQTemplate")
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    @Qualifier("payRocketMQTemplate")
    private PayRocketMQTemplate payRocketMQTemplate;

    @Autowired
    @Qualifier("mySelectorQueueRocketMQTemplate")
    private MySelectorQueueRocketMQTemplate mySelectorQueueRocketMQTemplate;


    /** 同步发送消息
     */
    @RequestMapping("/SyncMsg")
    public String sendSyncMsg() {
        for(int i=1; i<=5; i++) {
            rocketMQTemplate.syncSend(RocketmqConstant.TOPIC_SYNC, "同步消息" + i);
        }
        return JSON.toJSONString("ok", true);
    }
    /**发送顺序消息-使用默认的SelectMessageQueueByHash选择器(指定haskey参数进行hash计算后决定发送到哪个queue中)
     */
    @RequestMapping("/orderlyMsg")
    public String sendorderlyMsg() {
        //通过hasKey确定在哪个queue
        for(int i=1; i<=5; i++){
            rocketMQTemplate.syncSendOrderly(RocketmqConstant.TOPIC_ORDERLY, "orderMark顺序消息"+i, "orderMark",1000);
        }
        return JSON.toJSONString("ok", true);
    }
    /**发送顺序消费-自定义选择逻辑(通过取出queue数量根据指定的参数进行取余来发送到哪个queue中)*/
    @RequestMapping("/orderlyMsgBySelectorQueue")
    public String orderQueueNum() {
        for(int i=1; i<=5; i++) {
           /* mySelectorQueueRocketMQTemplate.setMessageQueueSelector((List<MessageQueue> list,Message message,Object o)->{
                log.info("使用MySelectorMessageQueue 选择Queue 发送顺序消息");
                int queueNum = Integer.valueOf(String.valueOf(o)) % list.size();
                return list.get(queueNum);
            });*/
            mySelectorQueueRocketMQTemplate.syncSendOrderly(RocketmqConstant.TOPIC_ORDERLY, "自定义选择器发送的顺序消息" + i,"1245455");
        }
        return JSON.toJSONString("ok", true);
    }


    /** 使用默认的rocketMQTemplate实例 发送事务消息
     * @return
     */
    @RequestMapping("/transactionMsg")
    public String transactionMsg() {
        try {
            for(int i=0;i<8;i++){
                TransactionSendResult transactionSendResult=rocketMQTemplate.sendMessageInTransaction(RocketmqConstant.TOPIC_TRANSACTION,
                        MessageBuilder.withPayload("默认的RocketMQTemplate发送的事务消息"+i).build(), null);
                log.info("transactionMsg 发送结果：{}", JSON.toJSONString(transactionSendResult));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString("ok", true);
    }

    /** 使用自定义RocketMQLocalTransactionListener的rocketMQTemplate实例 发送事务消息
     * @return
     */
    @RequestMapping("/paySuccessTranscationMsg")
    public String paySuccessTranscationMsg() {
        try {
            TransactionSendResult transactionSendResult = payRocketMQTemplate.sendMessageInTransaction(RocketmqConstant.TOPIC_PAYSUCESS, MessageBuilder.withPayload("使用支付payRocketMQTemplate发送事务消息").build(), null);
            log.info("paySuccessTranscationMsg 发送结果：{}", JSON.toJSONString(transactionSendResult));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString("ok", true);
    }
}
