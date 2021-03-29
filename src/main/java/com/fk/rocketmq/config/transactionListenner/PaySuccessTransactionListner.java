package com.fk.rocketmq.config.transactionListenner;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

/**
 * @Description: 自定义扩展的 payRocketMQTemplate 实例对应的事务Listner
 * @author: fk
 * @date: 2021年03月29日 10:42
 */
@RocketMQTransactionListener(rocketMQTemplateBeanName = "payRocketMQTemplate")
public class PaySuccessTransactionListner implements RocketMQLocalTransactionListener {
    private static Logger log = LoggerFactory.getLogger(GlobalTransactionListner.class);
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            log.info("自定义payRocketMQTemplate 实例的TransactionListner 执行 executeLocalTransaction 本地事务");
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        log.info("自定义payRocketMQTemplate 实例的TransactionListner 执行 checkLocalTransaction 事务回查");
        return RocketMQLocalTransactionState.COMMIT;
    }
}
