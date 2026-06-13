package com.satellite.change_detection_service.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.satellite.change_detection_service.dto.ClassificationRequest;
import com.satellite.change_detection_service.dto.ClassificationResponse;

;

@Service
public class ClassificationClientService {

    private final RestTemplate restTemplate;
    
   public ClassificationClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        
    }
    
    public ClassificationResponse classifyDeltas(List<Double> delta_array){
        ClassificationRequest request = new ClassificationRequest();
       
        request.setDelta_array(delta_array);
        ClassificationResponse response =restTemplate.postForObject
            (
                "http://localhost:8000/classify", 
                request,
                ClassificationResponse.class               
            );
         
        
        return response;

    }
}
