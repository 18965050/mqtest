package cn.xyz.lcg.activemq.simple.advance;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ConsumerBMessageListener implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		if (msg instanceof MapMessage) {
			MapMessage message = (MapMessage) msg;
			try {
				System.out.println("consumerB--MessageListener收到信息：" + new Date(message.getLong("count")));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
