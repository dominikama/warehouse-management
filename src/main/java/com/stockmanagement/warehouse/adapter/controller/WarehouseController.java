package com.stockmanagement.warehouse.adapter.controller;

import com.stockmanagement.warehouse.application.service.WarehouseService;
import com.stockmanagement.warehouse.application.dto.WarehouseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin(origins = "${frontend.url}")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public WarehouseDto create(@RequestBody WarehouseDto warehouseDto) {
       return warehouseService.create(warehouseDto);
    }

    @GetMapping
    public List<WarehouseDto> get() {
        return warehouseService.getAll();
    }

    @GetMapping("/{warehouseId}")
    public WarehouseDto getById(@PathVariable int warehouseId) {
        return warehouseService.get(warehouseId);
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity<Void> delete(@PathVariable int warehouseId) {
        warehouseService.delete(warehouseId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody WarehouseDto warehouseDto) {
        warehouseService.update(warehouseDto);
        return ResponseEntity.noContent().build();
    }
}
