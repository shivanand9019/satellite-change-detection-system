package com.satellite.ingestion_service.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.satellite.ingestion_service.dto.IngestionEvent;
@Service    
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public static final String topic = "satellite.ingest";
    String event = "Ingestion started for field 123";
    
    public String sendMessage(String topic,String event){
        
        try{
            kafkaTemplate.send(topic, event);
            return "Message sent successfully";
        }catch (Exception e){
            return "Failed to send message: " + e.getMessage();
        }
    }
}
 