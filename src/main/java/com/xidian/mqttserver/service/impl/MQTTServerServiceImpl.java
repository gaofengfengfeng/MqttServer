package com.xidian.mqttserver.service.impl;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xidian.mqttserver.entity.Message;
import com.xidian.mqttserver.entity.Topic;
import com.xidian.mqttserver.mapper.MessageMapper;
import com.xidian.mqttserver.mapper.TopicMapper;
import com.xidian.mqttserver.service.MQTTServerService;
import com.xidian.mqttserver.util.GenerateId;
import com.xidian.mqttserver.util.ServerSingleton;

@Service
public class MQTTServerServiceImpl implements MQTTServerService{
	
	@Autowired
	private TopicMapper topicMapper;
	@Autowired
	private MessageMapper messageMapper;

	@Override
	public void publish(String topic, int qos, boolean retained, String payload) throws MqttException {
		
		Message m = new Message();
		m.setId(GenerateId.getUUID());
		m.setTopic(topic);
		m.setQos(qos);
		m.setMessage(payload);
		m.setRetained((byte) (retained ? 1 : 0));
		
		messageMapper.insert(m);
		
		ServerSingleton serverSingleton = ServerSingleton.getInstance();
		
		MqttMessage message = new MqttMessage();
	    message.setQos(qos);
	    message.setRetained(retained);
	    message.setPayload(payload.getBytes());
	    
	    serverSingleton.publish(serverSingleton.getClient().getTopic(topic), message);
		
	}

	@Override
	public boolean addTopic(String topic) {
		
		Topic t = topicMapper.select(topic);
		
		if(t != null){
			return false;
		}
		
		topicMapper.insert(GenerateId.getUUID(), topic);
		return true;
	}

}
