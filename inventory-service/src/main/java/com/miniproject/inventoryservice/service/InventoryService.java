package com.miniproject.inventoryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.inventoryservice.dto.InventoryResponse;
import com.miniproject.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    @Transactional(readOnly = true)
     public List<InventoryResponse> isInStock(List<String> skuCode){
        log.info("Checking Inventory");
        return inventoryRepository.findbyskuCodeIn(skuCode).stream()
            .map(inventory ->
                InventoryResponse.builder()
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity() >0)
                    .build()
            ).toList();
     }
}
