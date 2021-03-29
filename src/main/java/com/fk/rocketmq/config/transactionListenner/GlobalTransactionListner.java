package com.fk.rocketmq.config.transactionListenner;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

/**
 * @Description: 默认的 RocketMQTemplate 实例对应的事务Listner
 * @author: fk
 * @date: 2021年03月29日 10:42
 */
@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")//默认值就是RocketMQTemplate可以不配置
public class GlobalTransactionListner implements RocketMQLocalTransactionListener {
    private static Logger log = LoggerFactory.getLogger(GlobalTransactionListner.class);

    /**
     * 执行本地事务
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        try {
            log.info("默认的 RocketMQTemplate TransactionListner 执行本地事务");
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    /**
     * 本地事务的检查，检查本地事务是否成功
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        //return RocketMQLocalTransactionState.ROLLBACK;
        return RocketMQLocalTransactionState.COMMIT;
    }

}
