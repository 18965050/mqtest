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

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

public class Producer {
	public static void main(String[] args) throws MQClientException, InterruptedException {
		DefaultMQProducer producer = new DefaultMQProducer("filterProducer");

		producer.setNamesrvAddr("centOS1:9876");

		// producer.setDefaultTopicQueueNums(1);

		producer.start();

		try {
			for (int i = 0; i < 10; i++) {
				Message msg = new Message("filterTest", // topic
						"TagA", // tag
						"OrderID001", // key
						("Hello MetaQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));// body

				msg.putUserProperty("SequenceId", String.valueOf(i));

				SendResult sendResult = producer.send(msg);
				System.out.println(sendResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		producer.shutdown();
	}
}
