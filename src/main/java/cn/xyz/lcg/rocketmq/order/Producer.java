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

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.MessageQueueSelector;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

public class Producer {
	public static void main(String[] args) throws UnsupportedEncodingException {
		try {
			DefaultMQProducer producer = new DefaultMQProducer("orderProducer");

			producer.setNamesrvAddr("centOS1:9876");

			// 设置队列数,和tag数一致, 这样观察比较直观
			producer.setDefaultTopicQueueNums(5);

			producer.start();

			String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };

			for (int i = 0; i < 20; i++) {

				int orderId = i % 10;
				// Message msg =
				// new Message("orderTest", tags[i % tags.length], "KEY" + i,
				// ("Hello RocketMQ " +
				// i).getBytes(RemotingHelper.DEFAULT_CHARSET));

				Message msg = new Message("orderTest", "*", "KEY" + i,
						("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

				System.out.println(new String(msg.getBody(), "UTF-8"));

				SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
					@Override
					public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
						Integer id = (Integer) arg;
						int index = id % mqs.size();
						return mqs.get(index);
					}
				}, orderId);

				// System.out.println(sendResult);
			}

			producer.shutdown();
		} catch (MQClientException e) {
			e.printStackTrace();
		} catch (RemotingException e) {
			e.printStackTrace();
		} catch (MQBrokerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
