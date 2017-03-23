package cn.xyz.lcg.activemq.simple.topic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PublisherMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-topic-producer.xml");
		PublisherService publisherService=(PublisherService)context.getBean("publisherService");
		publisherService.send();
	}

}
