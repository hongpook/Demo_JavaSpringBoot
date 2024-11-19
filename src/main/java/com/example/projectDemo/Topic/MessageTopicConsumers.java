package com.example.projectDemo.Topic;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageTopicConsumers {
    @JmsListener(destination = "Topic.example", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage(String message){
        System.out.println("Received message on Subscriber 1: " + message);
    }

    @JmsListener(destination = "Topic.example", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage2(String message){
        System.out.println("Received message on Subscriber 2: " + message);
    }

    @JmsListener(destination = "Topic.example", containerFactory = "topicListenerContainerFactory")
    public void receiveMessage3(String message){
        System.out.println("Received message on Subscriber 3: " + message);
    }
}

