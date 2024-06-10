package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.adapter.messaging.EventPublisher;
import com.stockmanagement.warehouse.adapter.messaging.KafkaEventPublisher;
import com.stockmanagement.warehouse.adapter.messaging.events.EventType;
import com.stockmanagement.warehouse.adapter.messaging.events.WarehouseEvent;
import com.stockmanagement.warehouse.adapter.persistance.WarehouseRepository;
import com.stockmanagement.warehouse.application.dto.WarehouseDto;
import com.stockmanagement.warehouse.domain.models.Warehouse;
import com.stockmanagement.warehouse.application.mapper.WarehouseMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class DefaultWarehouseService implements WarehouseService {
    private final WarehouseRepository warehouseRepository;

    private final EventPublisher eventPublisher;

    public DefaultWarehouseService(WarehouseRepository warehouseRepository, KafkaEventPublisher eventPublisher) {
        this.warehouseRepository = warehouseRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public WarehouseDto create(WarehouseDto request) {
        Warehouse warehouse = WarehouseMapper.toEntity(request, new Warehouse());
        warehouse.setCreatedAt(LocalDateTime.now());
        warehouse.setUpdatedAt(LocalDateTime.now());
        WarehouseDto dto = WarehouseMapper.toDto(warehouseRepository.save(warehouse));
        eventPublisher.publishEvent(new WarehouseEvent(warehouse.getNumber(), dto, EventType.WarehouseCreatedEvent));
        return dto;
    }

    @Override
    public List<WarehouseDto> getAll() {
        return warehouseRepository.findAll().stream()
                .map(WarehouseMapper::toDto)
                .toList();
    }

    @Override
    public WarehouseDto get(int id) {
        return warehouseRepository.findById(id)
                .map(WarehouseMapper::toDto)
                .orElseThrow(() -> new NoSuchElementException("Warehouse not existing"));
    }

    @Override
    public void delete(int id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                        .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        warehouseRepository.delete(warehouse);
        eventPublisher.publishEvent(new WarehouseEvent(warehouse.getNumber(), WarehouseMapper.toDto(warehouse),
                EventType.WarehouseDeletedEvent));
    }

    @Override
    public void update(WarehouseDto warehouseDto) {
        Warehouse warehouseToUpdate = warehouseRepository.findById(warehouseDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Warehouse not found"));
        warehouseRepository.save(WarehouseMapper.toEntity(warehouseDto, warehouseToUpdate));
        eventPublisher.publishEvent(new WarehouseEvent(warehouseDto.getNumber(), warehouseDto,
                EventType.WarehouseUpdateEvent));
    }
}
