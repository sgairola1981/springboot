package com.gairola.order.orderservice.repo;

import org.springframework.data.repository.CrudRepository;

import com.gairola.order.orderservice.model.OrderEntity;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

}
