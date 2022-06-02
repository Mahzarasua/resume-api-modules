package com.mahzarasua.resumeapi.resume.config;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
public class JmsConfiguration {
    private static String brokerUrl = "tcp://localhost:61616";

    @Bean(name = "jmsQueue")
    public Queue queue(){
        return new ActiveMQQueue("resume_queue");
    }

    @Bean
    public ConnectionFactory activeMQConnectionFactory(){
        ConnectionFactory factory = new ActiveMQConnectionFactory();
        ((ActiveMQConnectionFactory) factory).setBrokerURL(brokerUrl);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(activeMQConnectionFactory());
    }
}
