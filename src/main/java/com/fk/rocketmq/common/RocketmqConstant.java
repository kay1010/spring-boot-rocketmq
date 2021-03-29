package com.fk.rocketmq.common;

/**
 * @Description: TODO
 * @author: zhuqi
 * @date: 2020年09月01日 15:33
 */
public class RocketmqConstant {
    public static final String TOPIC_SYNC = "topic_sync";
    public static final String TOPIC_ASYNC = "topic_async";
    public static final String TOPIC_ORDERLY = "topic_orderly";
    public static final String TOPIC_PAYSUCESS = "topic_PaySuccess";
    public static final String TOPIC_TRANSACTION = "topic_transaction";

    public static final String PRODUCER_GROUP_ORDERLY = "producer_group_orderly";

    public static final String CONSUMER_GROUP_TEST = "group_test";
    public static final String CONSUMER_GROUP_SYNC = "group_sync";
    public static final String CONSUMER_ORDER_TEST = "group_order";
    public static final String CONSUMER_TRANSACTION_TEST = "group_transaction";
    public static final String PAY_PRODUCER_GROUP = "pay_group";
}
