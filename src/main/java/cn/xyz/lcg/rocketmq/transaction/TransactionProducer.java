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
package cn.xyz.lcg.rocketmq.transaction;

import java.io.UnsupportedEncodingException;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.client.producer.TransactionMQProducer;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.common.RemotingHelper;

public class TransactionProducer {
	public static void main(String[] args) throws MQClientException, InterruptedException {

		// 事务校验器.当RocketMQ发现'Prepared消息'时，会根据这个Listener实现的策略来决断事务
		TransactionCheckListener transactionCheckListener = new TransactionCheckListenerImpl();
		TransactionMQProducer producer = new TransactionMQProducer("transProducer");

		producer.setCheckThreadPoolMinSize(2);

		producer.setCheckThreadPoolMaxSize(2);

		producer.setCheckRequestHoldMax(2000);
		producer.setTransactionCheckListener(transactionCheckListener);

		producer.setDefaultTopicQueueNums(5);

		producer.setNamesrvAddr("centOS1:9876");
		producer.start();

		String[] tags = new String[] { "TagA", "TagB", "TagC", "TagD", "TagE" };
		TransactionExecuterImpl tranExecuter = new TransactionExecuterImpl();
		for (int i = 0; i < 10; i++) {
			try {
				Message msg = new Message("transTest", tags[i % tags.length], "KEY" + i,
						("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
				SendResult sendResult = producer.sendMessageInTransaction(msg, tranExecuter, null);
				System.out.println(sendResult);

				Thread.sleep(10);
			} catch (MQClientException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		// for (int i = 0; i < 100000; i++) {
		// Thread.sleep(1000);
		// }

		producer.shutdown();

	}
}
