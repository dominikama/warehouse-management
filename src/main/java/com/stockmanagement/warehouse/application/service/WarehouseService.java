package com.stockmanagement.warehouse.application.service;

import com.stockmanagement.warehouse.application.dto.WarehouseDto;

import java.util.List;

public interface WarehouseService {
    WarehouseDto create(WarehouseDto request);
    List<WarehouseDto> getAll();
    WarehouseDto get(int id);
    void delete(int id);
    void update(WarehouseDto warehouseDto);

}
