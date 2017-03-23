package cn.xyz.lcg.activemq.simple.advance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xyz.lcg.activemq.simple.topic.PublisherService;

public class ExclusiveConsumerProducerMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/advance/exclusiveconsumer-producer.xml");
		PublisherService publisherService=(PublisherService)context.getBean("publisherService");
		for(int i=0;i<10;i++){
			publisherService.send();
		}
		
	}

}
