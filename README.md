# spring-boot基于rocket-spring-starter整合rocketmq

## 普通消息实现
使用全局默认的RockTMQTemplate实例

## 顺序消息实现
默认的RockTMQTemplate实例
基于@ExtRocketMQTemplateConfiguration注解配置自定义MessageQueueSelector的RockTMQTemplate实例

## 事务消息
使用全局默认的RockTMQTemplate实例
基于@ExtRocketMQTemplateConfiguration注解配置不同TransactionListener的RockTMQTemplate实例

## 详情见CSDN博客专栏
https://blog.csdn.net/qq_29569183/category_10681623.html
