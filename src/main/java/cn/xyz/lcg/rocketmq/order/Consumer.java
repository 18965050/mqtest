/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cn.xyz.lcg.rocketmq.order;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerOrderly;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.consumer.ConsumeFromWhere;
import com.alibaba.rocketmq.common.message.MessageExt;

public class Consumer {

	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("orderConsumer8");

		consumer.setNamesrvAddr("centOS1:9876");

		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

		// 顺序消费时最好保持consumer消费线程和messageQueue一致
		consumer.setConsumeThreadMin(2);
		consumer.setConsumeThreadMax(2);

		// consumer.subscribe("orderTest", "TagA || TagB ||TagC || TagD
		// ||TagE");
		consumer.subscribe("orderTest", "*");

		consumer.registerMessageListener(new MessageListenerOrderly() {
			AtomicLong consumeTimes = new AtomicLong(0);

			@Override
			public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
				context.setAutoCommit(false);
				// System.out.println(Thread.currentThread().getName() + "
				// Receive New Messages: " + msgs);
				for (MessageExt msg : msgs) {
					try {
						System.out.println(Thread.currentThread().getName() + " " + new String(msg.getBody(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// ignore
					}

				}
				this.consumeTimes.incrementAndGet();
				// if ((this.consumeTimes.get() % 2) == 0) {
				// return ConsumeOrderlyStatus.SUCCESS;
				// } else if ((this.consumeTimes.get() % 3) == 0) {
				// return ConsumeOrderlyStatus.ROLLBACK;
				// } else if ((this.consumeTimes.get() % 4) == 0) {
				// return ConsumeOrderlyStatus.COMMIT;
				// } else if ((this.consumeTimes.get() % 5) == 0) {
				// context.setSuspendCurrentQueueTimeMillis(3000);
				// return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
				// }

				return ConsumeOrderlyStatus.SUCCESS;

			}
		});

		consumer.start();

		System.out.println("Consumer Started.");
	}

}
