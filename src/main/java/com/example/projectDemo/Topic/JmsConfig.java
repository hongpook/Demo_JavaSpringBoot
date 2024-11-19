package com.example.projectDemo.Topic;


import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public JmsListenerContainerFactory<?> topicListenerContainerFactory(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory defaultJmsListenerContainerFactory = new DefaultJmsListenerContainerFactory();
        defaultJmsListenerContainerFactory.setConnectionFactory(connectionFactory);
        defaultJmsListenerContainerFactory.setPubSubDomain(true);
        return  defaultJmsListenerContainerFactory;
    }

    @Bean
    JmsTemplate jsmTopicTemplate (ConnectionFactory connectionFactory){
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}
