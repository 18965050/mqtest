package cn.xyz.lcg.activemq.simple.advance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VTopicSubscriberListenerMain {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/advance/virtualtopic-consumerlistener.xml");
		System.in.read();
	}

}
