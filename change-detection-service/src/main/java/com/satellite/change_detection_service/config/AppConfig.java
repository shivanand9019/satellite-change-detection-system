package com.satellite.change_detection_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    @Bean
    RestTemplate methodReturningRestTemplate() {
        return new RestTemplate();
    }
    
}
