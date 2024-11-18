package com.example.projectDemo.ActiveMQ;

import org.springframework.jms.annotation.JmsListener;

public class MessageConsumer {
    @JmsListener(destination = "Queue.example")
    public void receiveMessage(Message message){
        System.out.println("Received message: " + message.getText());
    }
}
