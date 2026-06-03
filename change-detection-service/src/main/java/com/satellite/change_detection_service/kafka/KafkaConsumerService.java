package com.satellite.change_detection_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service

public class KafkaConsumerService {
    @KafkaListener(topics = "satellite.ingest", groupId = "change-detection-group")
    public void consumer(String message){
        System.out.println("Message received: " + message);
        System.out.println("Received Event:"+message);
        System.out.println(" Field ID   : " + message);
        System.out.println(" Date 1     : " + message);
        System.out.println(" Date 2     : " + message);
    }
    
}
