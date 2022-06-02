package com.mahzarasua.resumeapi.resume.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@Slf4j
public class KafkaProducerConfig {
   public static final String TOPIC = "work_experience";
    public static final String GROUP = "group_id";

    @Autowired
    private KafkaTemplate template;

   public void sendMessage(String message){
       log.info("Attempting to publish {} in Kafka", message);
       this.template.send(TOPIC, message);
   }

   @Bean
    public NewTopic adviceTopic(){
       return new NewTopic(TOPIC, 3, (short)1);
   }
}
