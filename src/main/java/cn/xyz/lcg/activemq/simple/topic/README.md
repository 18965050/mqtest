1. PubSub模式下, 要求consumer先注册(即先启动), 否则收不到publisher发送的消息

2. 为保证consumer在断开重连能收到错过的消息, 需要对consumer进行durable subscription配置. 此仅在异步(MessageListener)下, 可通过设置subscriptionDurable和clientId来实现. 
(注意: 
1. durable subscription并不是指consumer在publisher发出消息后再注册,可收到消息; 而是避免注册过的consumer断开重连后丢失消息
2. durable subscription和消息本身的persistent没有任何关系, 时PubSub模式下针对consumer特有的一种方式)