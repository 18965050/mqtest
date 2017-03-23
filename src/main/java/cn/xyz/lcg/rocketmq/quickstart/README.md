1. 连接远端的broker集群需要设置`producer.setNamesrvAddr("centOS1:9876");`或`consumer.setNamesrvAddr("centOS1:9876");`

2. Consumer分组, 不同的组针对同一个Topic可以设置consumer位置`consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);`. 如上设置中, 每一个分组都从头消费消息

3. consumer可以针对tag(注意: 不是分区, 分区在rocketmq中用queue来表示)进行消费. 匹配的分区可以消费消费, 否则不行

4. 数据默认存储在`~/store`目录下

5. 如果producer只设定一个queue(`producer.setDefaultTopicQueueNums(1);`), 并且consumer保证只有一个线程消费(`consumer.setConsumeThreadMax(1);`), 则也可保证消息顺序消费

5. PullConsumer不能设定组的消费位置, 其通过`consumer.pullBlockIfNotFound()`来指定队列的拉取位置.拉取状态通过在PullResult对象中. 因此, 和PushConsumer不同,即使在同一个consumer group中, 如果每次从头开始拉取,都能消费消息 

6. 定时消费通过MQPullConsumerScheduleService来实现

7. 异步发送通过producer的send()方法实现SendCallback接口
