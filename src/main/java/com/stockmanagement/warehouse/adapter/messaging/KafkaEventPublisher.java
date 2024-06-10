package com.stockmanagement.warehouse.adapter.messaging;

import com.stockmanagement.warehouse.adapter.messaging.events.WarehouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventPublisher implements EventPublisher{
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    public KafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(WarehouseEvent event) {
        kafkaTemplate.send(kafkaTopic, event.getWarehouseNumber(), event);
    }
}
