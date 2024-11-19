package com.example.projectDemo.Topic;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/topic")

public class MessageTopicController {

    private final MessageTopicProducer messageProducer;


    public MessageTopicController(MessageTopicProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/publish-message")
    public ResponseEntity<String> publishMessage(@RequestBody String messageText){
        try{
            messageProducer.sendMessageToTopic(messageText);
            return new ResponseEntity<>("Message publish successfully!", HttpStatus.OK) ;
        } catch (Exception e) {
            return new ResponseEntity<>("Error publish message!" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
