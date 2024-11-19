package com.example.projectDemo.Topic;


import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageTopicProducer {

    private final JmsTemplate jmsTemplate;


    public MessageTopicProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessageToTopic(String message){
        jmsTemplate.convertAndSend("Topic.example", message);
    }
}

