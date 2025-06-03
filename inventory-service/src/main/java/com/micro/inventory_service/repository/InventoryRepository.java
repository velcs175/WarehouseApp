package com.micro.inventory_service.repository;

import com.micro.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductCode(String productCode);
    
    //Optional<Inventory> findByProductCode(String productCode);
}
