package cn.xyz.lcg.activemq.cluster.failover;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xyz.lcg.activemq.simple.queue.ConsumerService;

public class ConsumerMain {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/cluster/failover/cluster-queue-consumer.xml");
		ConsumerService consumerService=(ConsumerService)context.getBean("consumerService");
		consumerService.receive();
		System.in.read();
	}

}
