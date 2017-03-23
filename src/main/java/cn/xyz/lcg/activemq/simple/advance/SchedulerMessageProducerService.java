package cn.xyz.lcg.activemq.simple.advance;

import java.util.Date;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ScheduledMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SchedulerMessageProducerService {
	
	JmsTemplate	jmsTemplate;

	Destination	destination;

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public void send(){
		MessageCreator messageCreator = new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				Date date = new Date();
				message.setLong("count", date.getTime());
				message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 3000);
				message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 1000);
				message.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 3);
				System.out.println("--发送消息：" + date);
				return message;
			}
		};
		jmsTemplate.send(this.destination, messageCreator);
	}
}
