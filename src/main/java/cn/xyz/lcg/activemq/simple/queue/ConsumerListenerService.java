package cn.xyz.lcg.activemq.simple.queue;

import java.util.Date;
import java.util.HashMap;

public class ConsumerListenerService {

	public void receive(HashMap message) {
		System.out.println("--Listener收到消息：" + new Date(new Long((Long) message.get("count"))));
	}

}
