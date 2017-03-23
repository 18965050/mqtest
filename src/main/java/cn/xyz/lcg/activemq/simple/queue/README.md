1. queue消息都是持久订阅的. producer发送消息,如果broker不重启, consumer可以稍后接收消息. 
如果producer发送消息后, broker重启, 需要看是否存储消息(persistent是否为true). 存储消息, 则broker重启后启动consumer可以接收消息, 否则不能接收消息

2. 可通过如下方式  `activemq [start|stop] xbean:<path>` 来指定加载的配置文件

3. consumer接收方式可为同步(MessageConsumer.receive())或异步(实现MessageListener接口方法),同步请求下, 可设定consumer的receiveTimeout. 
异步情况下, 此参数不起作用. 同步状态下, 只能接受最新的一条记录; 异步情况则可接受所有未消费的数据

4. web console只有在useJmx="true"的情况下,才会显示queue队列信息, 否则不会显示

5. 有个问题还未解决, 关闭broker时(`activemq stop`)会抛出rmi连接jmx异常, 估计和本机虚拟机运行activemq有关. 