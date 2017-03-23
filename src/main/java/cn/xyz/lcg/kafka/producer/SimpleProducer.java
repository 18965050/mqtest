package cn.xyz.lcg.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class SimpleProducer {

	private static Producer<Integer, String>	producer;
	private final Properties					props	= new Properties();

	public SimpleProducer() {
		//注意:broker配置文件server.properties中配置项advertised.host.name需要配置
		props.put("metadata.broker.list", "centOS1:9092,centOS1:9093");
		// props.put("metadata.broker.list", "test-213:9093, test-213:9094");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		/**
		 * 0: producer不等待broker ack
		 * 1: producer等待lead replica ack
		 * -1: producer等待all replica ack
		 */
		props.put("request.required.acks", "1");
		
		/**
		 * sync or async
		 */
		props.put("producer.type", "async");
		
		/**
		 * 批次条数,用于async模式
		 */
		props.put("batch.num.messages", "50");
		producer = new Producer<Integer, String>(new ProducerConfig(props));
	}

	public static void main(String[] args) {
		SimpleProducer sp = new SimpleProducer();
		String topic = "testtopic1";
		String messageStr = "helloworld";
		KeyedMessage<Integer, String> data = new KeyedMessage<Integer, String>(topic, messageStr);
		for(int i=0;i<100;i++){
			producer.send(data);
		}
		
		System.out.println("send");
		producer.close();
	}

}
