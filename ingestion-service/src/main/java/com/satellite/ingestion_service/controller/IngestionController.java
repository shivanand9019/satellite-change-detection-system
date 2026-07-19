package com.satellite.ingestion_service.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satellite.ingestion_service.dto.IngestionEvent;
import com.satellite.ingestion_service.kafka.KafkaProducerService;

@RestController
@RequestMapping("/ingest")
@CrossOrigin("*")
public class IngestionController {
  
    private final KafkaProducerService kafkaProducerService;

    IngestionController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService = kafkaProducerService;
    }
    
    @PostMapping("/trigger")
    public String triggerIngestion(@RequestBody IngestionEvent event){
        String result = kafkaProducerService.sendMessage(KafkaProducerService.topic, event);
        System.out.println(event);
        
        
        return result;
    }
}
