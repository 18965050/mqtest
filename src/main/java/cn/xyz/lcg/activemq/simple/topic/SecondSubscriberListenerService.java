package cn.xyz.lcg.activemq.simple.topic;

import java.util.Date;
import java.util.HashMap;

public class SecondSubscriberListenerService implements SubscriberListenerService {

	@Override
	public void receive(HashMap message) {
		System.out.println("--订阅者二Listener收到消息：" + new Date(new Long((Long) message.get("count"))));
	}

}
