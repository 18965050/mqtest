package cn.xyz.lcg.activemq.simple.advance;

import org.springframework.context.support.ClassPathXmlApplicationContext;



public class MessageGroupsProducerMain {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/advance/messagegroups-producer.xml");
		MessageGroupsProducerService publisherService=(MessageGroupsProducerService)context.getBean("publisherService");
		for(int i=0;i<10;i++){
			publisherService.sendGroup();
		}
		
//		publisherService.terminate();
		
		for(int i=0;i<10;i++){
			publisherService.send();
		}
	}

}
