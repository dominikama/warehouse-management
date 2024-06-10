package com.stockmanagement.warehouse.application.mapper;

import com.stockmanagement.warehouse.application.dto.WarehouseDto;
import com.stockmanagement.warehouse.domain.models.Address;
import com.stockmanagement.warehouse.domain.models.Warehouse;

public class WarehouseMapper {
    public static Warehouse toEntity(WarehouseDto warehouseDto, Warehouse warehouse) {
        warehouse.setName(warehouseDto.getName());
        warehouse.setNumber(warehouseDto.getNumber());
        Address address = warehouse.getAddress();
        if (address == null) {
            address = new Address();
        }
        address.setCountry(warehouseDto.getCountry());
        address.setCity(warehouseDto.getCity());
        address.setAddressDetails(warehouseDto.getAddress());
        address.setPostalCode(warehouseDto.getPostalCode());
        warehouse.setAddress(address);
        warehouse.setEnabled(warehouseDto.isEnabled());
        return warehouse;
    }

    public static WarehouseDto toDto(Warehouse warehouse) {
        Address address = warehouse.getAddress();
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setId(warehouse.getId());
        warehouseDto.setName(warehouse.getName());
        warehouseDto.setNumber(warehouse.getNumber());
        warehouseDto.setCountry(address.getCountry());
        warehouseDto.setCity(address.getCity());
        warehouseDto.setPostalCode(address.getPostalCode());
        warehouseDto.setAddress(address.getAddressDetails());
        warehouseDto.setEnabled(warehouse.isEnabled());
        return warehouseDto;
    }
}
