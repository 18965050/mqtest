package cn.xyz.lcg.activemq.simple.topic;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DurableSubscriptionMain {

	public static void main(String[] args) throws Exception{
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-topic-durable-subscription.xml");
		System.in.read();
	}

}
