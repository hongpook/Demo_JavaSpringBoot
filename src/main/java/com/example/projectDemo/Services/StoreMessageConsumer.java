package com.example.projectDemo.Services;

import com.example.projectDemo.Dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StoreMessageConsumer {

    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void processToDo(Store store) {
        log.info("Consumer> " + store);
    }
}