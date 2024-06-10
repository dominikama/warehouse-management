package com.stockmanagement.warehouse.adapter.messaging;

import com.stockmanagement.warehouse.adapter.messaging.events.WarehouseEvent;

public interface EventPublisher {
    void publishEvent(WarehouseEvent event);
}
