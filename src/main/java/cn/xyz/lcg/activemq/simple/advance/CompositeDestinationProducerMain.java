package cn.xyz.lcg.activemq.simple.advance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xyz.lcg.activemq.simple.topic.PublisherService;

public class CompositeDestinationProducerMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"cn/xyz/lcg/activemq/advance/composite-destination-producer.xml");
		PublisherService publisherService = (PublisherService) context.getBean("publisherService");
		publisherService.send();
	}

}
