package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.adapter.messaging.KafkaEventPublisher;
import com.stockmanagement.warehouse.adapter.messaging.events.EventType;
import com.stockmanagement.warehouse.adapter.messaging.events.WarehouseEvent;
import com.stockmanagement.warehouse.adapter.persistance.WarehouseRepository;
import com.stockmanagement.warehouse.application.dto.WarehouseDto;
import com.stockmanagement.warehouse.domain.models.Address;
import com.stockmanagement.warehouse.domain.models.Warehouse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DefaultWarehouseServiceTest {
    @Mock
    WarehouseRepository warehouseRepository;
    @Mock
    KafkaEventPublisher kafkaEventPublisher;
    @InjectMocks
    DefaultWarehouseService defaultWarehouseService;

    @Test
    void create() {
        Mockito.when(warehouseRepository.save(any(Warehouse.class))).thenReturn(create("W123", 1));
        defaultWarehouseService.create(new WarehouseDto());
        Mockito.verify(warehouseRepository, times(1)).save(any());
        ArgumentCaptor<WarehouseEvent> argumentCaptor = ArgumentCaptor.forClass(WarehouseEvent.class);
        Mockito.verify(kafkaEventPublisher, times(1)).publishEvent(argumentCaptor.capture());
        assertEquals(EventType.WarehouseCreatedEvent, argumentCaptor.getValue().getEventType());
    }

    @Test
    void getAll() {
        List<Warehouse> warehouses = List.of(create("W123", 1), create("W333", 2));
        Mockito.when(warehouseRepository.findAll()).thenReturn(warehouses);
        List<WarehouseDto> warehousesDtos = defaultWarehouseService.getAll();
        assertEquals(2, warehousesDtos.size());
        verify(warehouseRepository, times(1)).findAll();
    }

    @Test
    void get() {
        Warehouse warehouse = create("W123", 1);
        Mockito.when(warehouseRepository.findById(1)).thenReturn(Optional.of(warehouse));
        WarehouseDto warehouseDto = defaultWarehouseService.get(1);
        verify(warehouseRepository, times(1)).findById(1);
        assertEquals("W123", warehouseDto.getNumber());
    }

    @Test
    void delete() {
        Warehouse warehouse = create("W123", 1);
        Mockito.when(warehouseRepository.findById(1)).thenReturn(Optional.of(warehouse));
        defaultWarehouseService.delete(1);
        verify(warehouseRepository, times(1)).delete(warehouse);
        ArgumentCaptor<WarehouseEvent> argumentCaptor = ArgumentCaptor.forClass(WarehouseEvent.class);
        verify(kafkaEventPublisher, times(1)).publishEvent(argumentCaptor.capture());
        assertEquals(EventType.WarehouseDeletedEvent, argumentCaptor.getValue().getEventType());
    }

    @Test
    void update() {
        Warehouse warehouse = create("W123", 1);
        Mockito.when(warehouseRepository.findById(1)).thenReturn(Optional.of(warehouse));
        WarehouseDto warehouseDtoToUpdate = new WarehouseDto();
        warehouseDtoToUpdate.setId(1);
        warehouseDtoToUpdate.setNumber("W124");

        defaultWarehouseService.update(warehouseDtoToUpdate);

        verify(warehouseRepository).findById(1);
        verify(warehouseRepository).save(warehouse);

        ArgumentCaptor<WarehouseEvent> argumentCaptor = ArgumentCaptor.forClass(WarehouseEvent.class);
        verify(kafkaEventPublisher, times(1)).publishEvent(argumentCaptor.capture());

        assertEquals(EventType.WarehouseUpdateEvent, argumentCaptor.getValue().getEventType());
        assertEquals("W124", warehouse.getNumber());
    }

    private  Warehouse create(String code, int id) {
        Warehouse warehouse = new Warehouse();
        Address address = new Address();
        warehouse.setNumber(code);
        warehouse.setId(id);
        warehouse.setAddress(address);
        return warehouse;
    }
}