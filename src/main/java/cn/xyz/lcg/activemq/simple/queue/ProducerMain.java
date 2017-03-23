package cn.xyz.lcg.activemq.simple.queue;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProducerMain {
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-queue-producer.xml");
		ProducerService prodService=(ProducerService)context.getBean("producerService");
		prodService.send();
	}

}
