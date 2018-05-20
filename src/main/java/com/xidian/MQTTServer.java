package com.xidian;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.xidian.mqttserver.util.ServerSingleton;

@Component
@Order(value = 1)
public class MQTTServer implements ApplicationRunner{

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		ServerSingleton serverSingleton = ServerSingleton.getInstance();
		
		MqttMessage message = new MqttMessage();
	    message.setQos(1);
	    message.setRetained(true);
	    message.setPayload("hello".getBytes());
	    
	    serverSingleton.publish(serverSingleton.getClient().getTopic("topic"), message);

        System.out.println(message.isRetained() + "------ratained状态");
		
	}
	
}
