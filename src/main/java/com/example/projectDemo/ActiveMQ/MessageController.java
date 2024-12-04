package com.example.projectDemo.ActiveMQ;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq")
public class MessageController {

    private final MessageProducer messageProducer;
    private final MessageConsumer messageConsumer;


    public MessageController(MessageProducer messageProducer, MessageConsumer messageConsumer) {
        this.messageProducer = messageProducer;
        this.messageConsumer = messageConsumer;
    }

    @PostMapping("/publish-message")
    public ResponseEntity<String> publishMessage(@RequestBody String messageText){
        Message message = new Message(messageText);
        try{
            messageProducer.sendMessage(message);
            messageConsumer.receiveMessage(message);
            return new ResponseEntity<>("Message publish successfully!", HttpStatus.OK) ;
        } catch (Exception e) {
            return new ResponseEntity<>("Error publish message!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
