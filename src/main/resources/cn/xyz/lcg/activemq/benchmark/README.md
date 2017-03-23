benchmark 使用jmeter工具, 测试安装步骤如下:
1. 将activemq-all-5.6.0.jar拷贝至%JMETER_HOME%/lib目录下
2. 测试计划中先添加线程组, 再可以在线程组中添加jms p2p sampler或pub sub sampler
3. p2p中的jndi配置我是直接在界面中配置的
4. pub sub中的destination配置需要以`dynamicTopics/`开头.(p2p如果不使用jndi需要以`dynamicQueues/`开头)
4. 不要通过GUI来测试, 可通过`jmeter -n -t <配置文件>`的方式来测试