package com.serjnn.DiscountService.kafka;


import com.serjnn.DiscountService.dto.DiscountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSender {
    private final KafkaTemplate<String, DiscountDto> kafkaTemplate;




    public void sendNewDiscount(String topicName, DiscountDto discountDto){
        System.out.println("sending " + discountDto);
        kafkaTemplate.send(topicName,discountDto);
    }




}
