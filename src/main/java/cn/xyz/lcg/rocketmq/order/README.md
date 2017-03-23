1. 为了演示直观, 我修改了producer默认的队列(对应kafka中的分区)个数(`producer.setDefaultTopicQueueNums(5);`)

2. 消费方可以只消费对应tag中的消息. 

3. 顺序消息通过发送方对消息进行通道分配, 并且消费方通过实现MessageListenerOrderly接口来实现queue中消息的顺序消费. 注意, 不同queue消息并不顺序(这点和kafka分区顺序概念一致)

4. ConsumeOrderlyStatus.SUCCESS 和 ConsumeOrderlyStatus.COMMIT有什么区别?