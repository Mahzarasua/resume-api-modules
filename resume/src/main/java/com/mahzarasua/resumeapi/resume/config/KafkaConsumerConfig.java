package com.mahzarasua.resumeapi.resume.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaConsumerConfig {
    @KafkaListener(topics = KafkaProducerConfig.TOPIC)
    public void consume(String message){
        log.info("Consumer received the message {}", message);
    }
}
