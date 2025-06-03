package com.micro.inventory_service.service;

import com.micro.inventory_service.model.Inventory;
import com.micro.inventory_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public boolean isStockAvailable(String productCode, int quantity) {
        return inventoryRepository.findByProductCode(productCode)
                .map(inventory -> inventory.getQuantity() >= quantity)
                .orElse(false);
    }

    public Optional<Inventory> getInventoryByProductCode(String productCode) {
        return inventoryRepository.findByProductCode(productCode);
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }
}
