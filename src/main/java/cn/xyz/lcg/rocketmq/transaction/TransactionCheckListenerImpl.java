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

import java.util.concurrent.atomic.AtomicInteger;

import com.alibaba.rocketmq.client.producer.LocalTransactionState;
import com.alibaba.rocketmq.client.producer.TransactionCheckListener;
import com.alibaba.rocketmq.common.message.MessageExt;

/**
 * 未决事务，服务器回查客户端，broker端发起请求代码没有被调用，所以此处代码可能没用。
 * @author lvchenggang
 *
 */
public class TransactionCheckListenerImpl implements TransactionCheckListener {
	private AtomicInteger transactionIndex = new AtomicInteger(0);

	@Override
	public LocalTransactionState checkLocalTransactionState(MessageExt msg) {
		System.out.println("server checking TrMsg " + msg.toString());

		int value = transactionIndex.getAndIncrement();
		if ((value % 6) == 0) {
			throw new RuntimeException("Could not find db");
		} else if ((value % 5) == 0) {
			// return LocalTransactionState.ROLLBACK_MESSAGE;
			return LocalTransactionState.COMMIT_MESSAGE;
		} else if ((value % 4) == 0) {
			// return LocalTransactionState.COMMIT_MESSAGE;
			return LocalTransactionState.ROLLBACK_MESSAGE;
		}

		return LocalTransactionState.UNKNOW;
	}
}