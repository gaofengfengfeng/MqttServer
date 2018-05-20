package com.xidian.mqttserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.xidian.mqttserver.entity.Message;

@Mapper
public interface MessageMapper {
	
	@Insert("INSERT INTO message(id, topic, message, qos, retained) "
			+ "VALUES(#{id}, #{topic}, #{message}, #{qos}, #{retained})")
	int insert(Message message);

}
