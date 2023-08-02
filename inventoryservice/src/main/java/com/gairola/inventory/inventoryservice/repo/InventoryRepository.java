package com.gairola.inventory.inventoryservice.repo;

import org.springframework.data.repository.CrudRepository;

import com.gairola.inventory.inventoryservice.model.Inventory;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Iterable<Inventory> findByItem(String item);
}