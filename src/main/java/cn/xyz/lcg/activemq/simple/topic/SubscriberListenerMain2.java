package cn.xyz.lcg.activemq.simple.topic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SubscriberListenerMain2 {

	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-topic-consumerlistener-2.xml");
		System.in.read();
	}

}
