package com.satellite.change_detection_service.kafka;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    Random random = new Random();
    
    @KafkaListener(topics = "satellite.ingest", groupId = "change-detection-group")
    public void consumer(String message){
        List<Double> ndviDate1 = new ArrayList<>();
        List<Double> ndviDate2 = new ArrayList<>();
        int totalPixels = 400;
        
        for(int i = 0; i < totalPixels; i++){
            ndviDate1.add(Math.round(random.nextDouble(0.1, 0.8) * 100.0) / 100.0);
            ndviDate2.add(Math.round(random.nextDouble(0.1, 0.8) * 100.0) / 100.0);
        }

        List<Double> deltaList = new ArrayList<>();
        int growthPixels = 0;
        int stressPixels = 0;
        int noChangePixels = 0;
        int significantPixels = 0;

        for(int i = 0; i < ndviDate1.size(); i++){
            double rawDelta = ndviDate2.get(i) - ndviDate1.get(i);
            // fix: round the delta to 2 decimal places to clear floating-point trailing digits
            double delta = Math.round(rawDelta * 100.0) / 100.0;
            deltaList.add(delta);
            
            if(delta > 0.15){
                growthPixels++;
                significantPixels++;
            } else if(delta < -0.15){
                stressPixels++;
                significantPixels++;
            } else {
                noChangePixels++;
            }
        }
        
        for(int i=0;i<5 && i<deltaList.size();i++){
            double delta = deltaList.get(i);
            System.out.println(String.format("%.2f", delta) + " ");
        }
       
        
        double growthPercentage = (growthPixels / (double) totalPixels) * 100;
        double stressPercentage = (stressPixels / (double) totalPixels) * 100;
        double noChangePercentage = (noChangePixels / (double) totalPixels) * 100;
        double significantPercentage = (significantPixels / (double) totalPixels) * 100;

        System.out.println("Growth Pixels: " + growthPixels + " (" + String.format("%.2f", growthPercentage) + "%)");
        System.out.println("Stress Pixels: " + stressPixels + " (" + String.format("%.2f", stressPercentage) + "%)");
        System.out.println("No Change Pixels: " + noChangePixels + " (" + String.format("%.2f", noChangePercentage) + "%)");
        System.out.println("Significant Change Pixels: " + significantPixels + " (" + String.format("%.2f", significantPercentage) + "%)");
    }
}