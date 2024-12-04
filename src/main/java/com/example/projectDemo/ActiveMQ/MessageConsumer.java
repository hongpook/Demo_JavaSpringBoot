package com.example.projectDemo.ActiveMQ;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageConsumer {

    @JmsListener(destination = "Queue.example")
    public void receiveMessage(Message message){
        MessageConsumer.log.info("Received message: " + message.getText());
    }
}
