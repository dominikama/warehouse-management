package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.application.dto.WarehouseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Profile("local")
public class LocalWarehouseService implements WarehouseService {
    private final Map<Integer, WarehouseDto> warehouses = new HashMap<>();
    @Override
    public WarehouseDto create(WarehouseDto request) {
        int index = warehouses.size();
        request.setId(index);
        warehouses.put(index, request);
        return request;
    }

    @Override
    public List<WarehouseDto> getAll() {
        return warehouses.values().stream().toList();
    }

    @Override
    public WarehouseDto get(int id) {
        return warehouses.get(id);
    }

    @Override
    public void delete(int id) {
        warehouses.remove(id);
    }

    @Override
    public void update(WarehouseDto warehouseDto) {
       warehouses.replace(warehouseDto.getId(), warehouseDto);
    }
}
