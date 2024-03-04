package com.miniproject.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import com.miniproject.inventoryservice.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,Long>{
   // Optional<Inventory> findbyskuCode(String skuCode);
    Optional<Inventory> findbyskuCodeIn(List<String> skuCode);

}