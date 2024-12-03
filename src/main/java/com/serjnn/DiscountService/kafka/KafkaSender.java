package com.serjnn.DiscountService.kafka;


import com.serjnn.DiscountService.dto.DiscountChangesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSender {
    private final KafkaTemplate<String, DiscountChangesDto> kafkaTemplate;

    public void sendNewDiscount(String topicName, DiscountChangesDto discountChangesDto) {
        System.out.println("sending " + discountChangesDto);
        kafkaTemplate.send(topicName, discountChangesDto);
    }




}
