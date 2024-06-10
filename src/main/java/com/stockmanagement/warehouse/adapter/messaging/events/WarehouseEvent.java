package com.stockmanagement.warehouse.adapter.messaging.events;

import com.stockmanagement.warehouse.application.dto.WarehouseDto;

import java.io.Serializable;
import java.time.Instant;

public class WarehouseEvent implements Serializable {
    private Instant timestamp;
    private String warehouseNumber;
    private WarehouseDto warehouseDto;
    private EventType eventType;

    public WarehouseEvent() {
    }

    public WarehouseEvent(String warehouseNumber, WarehouseDto warehouseDto, EventType eventType) {
        this.warehouseNumber = warehouseNumber;
        this.warehouseDto = warehouseDto;
        this.eventType = eventType;
        this.timestamp = Instant.now();
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public String getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(String warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public WarehouseDto getWarehouseDto() {
        return warehouseDto;
    }

    public void setWarehouseDto(WarehouseDto warehouseDto) {
        this.warehouseDto = warehouseDto;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "WarehouseEvent{" +
                "timestamp=" + timestamp +
                ", warehouseNumber='" + warehouseNumber + '\'' +
                ", warehouseDto=" + warehouseDto +
                ", eventType=" + eventType +
                '}';
    }
}

