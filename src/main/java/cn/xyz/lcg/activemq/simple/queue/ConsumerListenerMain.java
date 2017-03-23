package cn.xyz.lcg.activemq.simple.queue;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerListenerMain {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-queue-consumerlistener-1.xml");
		System.in.read();
	}

}
