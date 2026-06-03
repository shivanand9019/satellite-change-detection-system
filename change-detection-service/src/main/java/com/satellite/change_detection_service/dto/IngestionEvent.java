package com.satellite.change_detection_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngestionEvent{
    private Integer fieldId;
    private LocalDate date1;
    private LocalDate date2;
    
}