package com.xidian.mqttserver.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ServerSingleton {

	private static ServerSingleton instance;

	//tcp://MQTT安装的服务器地址:MQTT定义的端口号
	public static final String HOST = "tcp://192.168.100.200:1883";
	//定义一个主题
	public static final String TOPIC = "topic11";
	//定义MQTT的ID，可以在MQTT服务配置中指定
	private static final String clientid = "server11";

	private MqttClient client;
	private MqttTopic topic11;
	private String userName = "userName";
	private String passWord = "123456";

	private MqttMessage message;

	private ServerSingleton() throws MqttException{
		// MemoryPersistence设置clientid的保存形式，默认为以内存保存
		client = new MqttClient(HOST, clientid, new MemoryPersistence());
		connect();

	}

	/**
	 *  用来连接服务器
	 */
	private void connect() {
		MqttConnectOptions options = new MqttConnectOptions();
		options.setCleanSession(false);
		options.setUserName(userName);
		options.setPassword(passWord.toCharArray());
		// 设置超时时间
		options.setConnectionTimeout(10);
		// 设置会话心跳时间
		options.setKeepAliveInterval(20);
		try {
			client.setCallback(new PushCallback());
			client.connect(options);
			topic11 = client.getTopic(TOPIC);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *
	 * @param topic
	 * @param message
	 * @throws MqttPersistenceException
	 * @throws MqttException
	 */
	public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
	MqttException {
		MqttDeliveryToken token = topic.publish(message);
		token.waitForCompletion();
		System.out.println("message is published completely! "
				+ token.isComplete());
	}

	public MqttClient getClient() {
		return client;
	}

	public static synchronized ServerSingleton getInstance() throws MqttException{
		if (instance == null) {
			instance = new ServerSingleton();
		}
		return instance;
	}

}
