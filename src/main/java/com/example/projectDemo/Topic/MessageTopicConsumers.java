package com.example.projectDemo.Topic;


import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageTopicConsumers {
    @JmsListener(destination = "Topic.example", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage(String message){
        MessageTopicConsumers.log.info("Received message on Subscriber 1: " + message);
    }

    @JmsListener(destination = "Topic.example", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage2(String message){
        MessageTopicConsumers.log.info("Received message on Subscriber 2: " + message);
    }

    @JmsListener(destination = "Topic.example", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage3(String message){
        MessageTopicConsumers.log.info("Received message on Subscriber 3: " + message);
    }
}

