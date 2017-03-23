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
package cn.xyz.lcg.rocketmq.filter;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

public class Consumer {

	public static void main(String[] args) throws InterruptedException, MQClientException, IOException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filterConsumer");

		consumer.setNamesrvAddr("centOS1:9876");

		// consumer.setConsumeThreadMin(1);
		// consumer.setConsumeThreadMax(1);

		InputStream is = Consumer.class.getResourceAsStream("/cn/xyz/lcg/rocketmq/filter/MessageFilterImpl.java");

		String filterCode = IOUtils.toString(is);

		consumer.subscribe("filterTest", "cn.xyz.lcg.rocketmq.filter.MessageFilterImpl", filterCode);

		// consumer.subscribe("TopicFilter7", "*");

		consumer.registerMessageListener(new MessageListenerConcurrently() {

			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				if (CollectionUtils.isNotEmpty(msgs)) {
					for (MessageExt msg : msgs) {
						try {
							System.out.println(
									Thread.currentThread().getName() + " " + new String(msg.getBody(), "UTF-8"));
						} catch (UnsupportedEncodingException e) {
							// ignore
						}

					}
				}
				// System.out.println(Thread.currentThread().getName() + "
				// Receive New Messages: " + msgs);
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});

		consumer.start();

		System.out.println("Consumer Started.");
	}
}
