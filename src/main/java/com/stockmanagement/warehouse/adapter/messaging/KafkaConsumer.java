package com.stockmanagement.warehouse.adapter.messaging;

import com.stockmanagement.warehouse.adapter.messaging.events.WarehouseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "warehouse-events", groupId = "warehouse-group")
    public void listen(WarehouseEvent event) {
        logger.info("Received event: {}", event);
    }
}
