package com.xidian.mqttserver.service;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface MQTTServerService {
	
	
	public void publish(String topic, int qos, boolean retained, String payload) throws MqttException;

	
	public boolean addTopic(String topic);

}
