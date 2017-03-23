2m-noslave:
1. broker连接的namesrv可以配置多个, 当第一个可以连接即设置为master namesrv, 取余为slave namesrv

2. brokerId配置为0,表示master broker, 非0表示slave broker. 一个集群中brokerId和brokerName不能重复

3. 当集群中存在多个master broker情况下, 消息默认采用均分的方式存储在各个broker中. 当某个broker崩溃, 存放在此的消息不能消费

4. 在集群模式下, 发现重启push consumer会出现消息重复消费的问题

5. 在async master/slave模式下, 如果master崩溃, 会导致由于消息还没有刷到slave中而出现不能消费的情况. 在sync master/slave中则没有, 但仍存在消息重复消费的情况