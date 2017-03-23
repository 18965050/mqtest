package cn.xyz.lcg.activemq.simple.topic;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class PublisherService {
	
	JmsTemplate	jmsTemplate;

	Destination	destination;

	public void send() {
		MessageCreator messageCreator = new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				Date date = new Date();
				message.setLong("count", date.getTime());
				System.out.println("--发送消息：" + date);
				return message;
			}
		};
		jmsTemplate.send(this.destination, messageCreator);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

}
