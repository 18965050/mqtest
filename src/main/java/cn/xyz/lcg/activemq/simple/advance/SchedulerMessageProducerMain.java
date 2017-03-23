package cn.xyz.lcg.activemq.simple.advance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SchedulerMessageProducerMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"cn/xyz/lcg/activemq/advance/schedulermessage-producer.xml");
		SchedulerMessageProducerService publisherService = (SchedulerMessageProducerService) context
				.getBean("publisherService");
		publisherService.send();

	}

}
