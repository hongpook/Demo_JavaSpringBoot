package com.example.projectDemo.Controllers.admin;


import com.example.projectDemo.Dto.Store;
import com.example.projectDemo.Services.StoreMessageConsumer;
import com.example.projectDemo.Services.StoreMessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class StoreMessageController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    StoreMessageProducer storeMessageProducer;
    StoreMessageConsumer storeMessageConsumer;

    @Value("${activemq.destination}")
    private String destination;

    /**
     * API for publish message for ActiveMQ queue
     * @param store
     * @return String
     */
    @PostMapping("/publish")
    public String publishMessage(@RequestBody Store store){
        storeMessageProducer.sendTo(destination,store);
        return "Success";
    }

    @PostMapping("/subscribe")
    public String subscribeMessage(@RequestBody Store store){
        storeMessageConsumer.processToDo(store);
        return "Success";
    }
}