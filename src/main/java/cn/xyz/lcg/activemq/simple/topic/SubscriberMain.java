package cn.xyz.lcg.activemq.simple.topic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SubscriberMain {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cn/xyz/lcg/activemq/simple/simple-topic-consumer.xml");
		
		ExecutorService executorService=Executors.newFixedThreadPool(2, new ThreadFactory(){
			@Override
			public Thread newThread(Runnable r) {
				 Thread thread = new Thread(r, "subscriber: " + r);
		            thread.setDaemon(true);
		            return thread;
			}
		});
		
		SubscriberService firstSubscriberService=(SubscriberService)context.getBean("firstSubscriberService");
		
		SubscriberService secondSubscriberService=(SubscriberService)context.getBean("secondSubscriberService");
		
		executorService.execute(new Runnable(){
			@Override
			public void run() {
				firstSubscriberService.receive();
			}
		});
		
		executorService.execute(new Runnable(){
			@Override
			public void run() {
				secondSubscriberService.receive();
			}
		});
		
		System.in.read();
	}

}
