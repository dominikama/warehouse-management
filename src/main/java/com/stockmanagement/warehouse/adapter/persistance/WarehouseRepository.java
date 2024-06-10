package com.stockmanagement.warehouse.adapter.persistance;

import com.stockmanagement.warehouse.domain.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
}
