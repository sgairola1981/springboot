package com.gairola.inventory.inventoryservice.model;

public class InventoryEvent {

    private String type;

    private CustomerOrder order;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public void setOrder(CustomerOrder order) {
        this.order = order;
    }

}