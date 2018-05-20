package com.xidian.mqttserver.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xidian.mqttserver.entity.Topic;
import org.springframework.stereotype.Component;

@Mapper
public interface TopicMapper {
	
	@Insert("INSERT INTO TOPIC(id, topic_name) VALUES(#{id}, #{topicName})")
    int insert(@Param("id") String id, @Param("topicName") String topicName);
	
	@Select("select * from topic where topic_name = #{topic}")
	Topic select(String topic);

}
