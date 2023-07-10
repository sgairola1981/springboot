package com.gairola.inventory.inventoryservice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    Iterable<Inventory> findByItem(String item);
}