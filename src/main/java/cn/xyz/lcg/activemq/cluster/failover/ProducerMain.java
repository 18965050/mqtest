package cn.xyz.lcg.activemq.cluster.failover;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xyz.lcg.activemq.simple.queue.ProducerService;

public class ProducerMain {
	
	public static void main(String[] args){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/cluster/failover/cluster-queue-producer.xml");
		ProducerService prodService=(ProducerService)context.getBean("producerService");
		prodService.send();
	}

}
