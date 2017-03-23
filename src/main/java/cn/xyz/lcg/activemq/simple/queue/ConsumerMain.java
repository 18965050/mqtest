package cn.xyz.lcg.activemq.simple.queue;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerMain {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-queue-consumer.xml");
		ConsumerService consumerService=(ConsumerService)context.getBean("consumerService");
		consumerService.receive();
		System.in.read();
	}

}
