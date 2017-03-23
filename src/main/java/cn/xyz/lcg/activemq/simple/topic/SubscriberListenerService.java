package cn.xyz.lcg.activemq.simple.topic;

import java.util.HashMap;

public interface SubscriberListenerService {

	void receive(HashMap message);

}
