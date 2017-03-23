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

package cn.xyz.lcg.rocketmq.quickstart;

import org.apache.commons.collections.CollectionUtils;

import com.alibaba.rocketmq.client.consumer.MQPullConsumer;
import com.alibaba.rocketmq.client.consumer.MQPullConsumerScheduleService;
import com.alibaba.rocketmq.client.consumer.PullResult;
import com.alibaba.rocketmq.client.consumer.PullTaskCallback;
import com.alibaba.rocketmq.client.consumer.PullTaskContext;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.common.message.MessageQueue;
import com.alibaba.rocketmq.common.protocol.heartbeat.MessageModel;


public class PullScheduleConsumer {

    public static void main(String[] args) throws MQClientException {
        final MQPullConsumerScheduleService scheduleService = new MQPullConsumerScheduleService("schedulerPullConsumer");

        scheduleService.setMessageModel(MessageModel.CLUSTERING);
        
        scheduleService.getDefaultMQPullConsumer().setNamesrvAddr("centOS1:9876");

        
        scheduleService.registerPullTaskCallback("simpleTest", new PullTaskCallback() {

            @Override
            public void doPullTask(MessageQueue mq, PullTaskContext context) {
                MQPullConsumer consumer = context.getPullConsumer();
                try {

                    long offset = consumer.fetchConsumeOffset(mq, false);
                    if (offset < 0)
                        offset = 0;

                    PullResult pullResult = consumer.pull(mq, "*", offset, 6);
//                    System.out.println(offset + "\t" + mq + "\t" + pullResult);
                    if(CollectionUtils.isNotEmpty(pullResult.getMsgFoundList())){
                    	for(MessageExt msg:pullResult.getMsgFoundList()){
                    		System.out.println(Thread.currentThread().getName() + " " +new String(msg.getBody(),"UTF-8"));
                    	}
                    }
                    switch (pullResult.getPullStatus()) {
                        case FOUND:
                            break;
                        case NO_MATCHED_MSG:
                            break;
                        case NO_NEW_MSG:
                        case OFFSET_ILLEGAL:
                            break;
                        default:
                            break;
                    }
                    consumer.updateConsumeOffset(mq, pullResult.getNextBeginOffset());


                    context.setPullNextDelayTimeMillis(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        scheduleService.start();
    }
}
