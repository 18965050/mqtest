1.virtualTopic配置要点:
	a. producer端发送的destination类型为topic类型, 名称以"VirtualTopic."开头; consumer端接收的destination类型为queue类型, 名称需符合"Consumer.XXX.VirtualTopic.XXX"格式
	b. 当consumer配置为同一个queue时, 会均分消息; 否则每个consumer接收所有消息
	
2. compositeDestination配置要点: 可同时配置多个queue或topic, 如果是queue域配置topic需要加上`topic://`,反之则加上`queue://`

3. exclusiveConsumer针对queue域的消息只发送给一个consumer上, 而不是均分到各个consumer上. 只需要在consumer的queue配置上添加`consumer.exclusive=true`参数即可

4. messageGroups可以看做是exclusiveConsumer的升级版(针对queue域), 通过设置message的JMSXGroupID, 相同groupId的消息会被发送给同一个consumer

5. scheduer message配置要点:
	a. broker需要配置schedulerSupport=true
	b. 消息中可配置ScheduledMessage.AMQ_SCHEDULED_DELAY, ScheduledMessage.AMQ_SCHEDULED_PERIOD, ScheduledMessage.AMQ_SCHEDULED_REPEAT等属性
