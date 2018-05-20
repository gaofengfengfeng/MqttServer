package com.xidian.mqttserver.controller;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xidian.mqttserver.entity.ResponseObject;
import com.xidian.mqttserver.service.MQTTServerService;

@RestController
@RequestMapping(value = "/api")
public class MQTTServerController {
	
	@Autowired
	private MQTTServerService mQTTServerService;
	
	@RequestMapping(value = "/publish")
	public ResponseObject publish(String topic, String message) throws MqttException{
		mQTTServerService.publish(topic, 1, true, message);
		return ResponseObject.newSuccessResponseObject("消息发布成功");
	}
	
	@RequestMapping(value = "/addTopic")
	public ResponseObject addTopic(String topic){
		
		boolean flag = mQTTServerService.addTopic(topic);
		
		return flag ? ResponseObject.newSuccessResponseObject("话题创建成功！") : ResponseObject.newErrorResponseObject("话题已存在，创建失败！");
		
	}

}
