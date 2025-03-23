package com.micro.inventory_service.controller;

import com.micro.inventory_service.model.Inventory;
import com.micro.inventory_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }
    
    @GetMapping("/check")
    public boolean checkStock(@RequestParam String productCode, @RequestParam int quantity) {
        return inventoryService.isStockAvailable(productCode, quantity);
    }

    @GetMapping("/{skuCode}")
    public Optional<Inventory> getInventoryBySkuCode(@PathVariable String skuCode) {
        return inventoryService.getInventoryBySkuCode(skuCode);
    }

    @PostMapping
    public Inventory addInventory(@RequestBody Inventory inventory) {
        return inventoryService.saveInventory(inventory);
    }

    @DeleteMapping("/{id}")
    public String deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return "Inventory deleted successfully";
    }
}
