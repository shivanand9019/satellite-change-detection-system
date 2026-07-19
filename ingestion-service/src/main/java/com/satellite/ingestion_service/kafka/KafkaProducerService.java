package com.satellite.ingestion_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.satellite.ingestion_service.dto.IngestionEvent;
@Service    
public class KafkaProducerService {

    private final KafkaTemplate<String,IngestionEvent> kafkaTemplate;
    KafkaProducerService(KafkaTemplate<String, IngestionEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public static final String topic = "satellite.ingest";
    
    public String sendMessage(String topic,IngestionEvent event){
        
        try{
           kafkaTemplate.send(topic,event);
            return "Message sent successfully";
        }catch (Exception e){
            return "Failed to send message: " + e.getMessage();
        }
    }
}
 