package com.satellite.change_detection_service.repository;

import org.springframework.stereotype.Repository;
import com.satellite.change_detection_service.entity.Alert;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

    public Alert save(Alert alert);
    public List<Alert> findAll();

    
}
