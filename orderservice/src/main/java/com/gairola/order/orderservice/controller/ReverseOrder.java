package com.gairola.order.orderservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gairola.order.orderservice.model.OrderEntity;
import com.gairola.order.orderservice.model.OrderEvent;
import com.gairola.order.orderservice.repo.OrderRepository;

@Component
public class ReverseOrder {

    @Autowired
    private OrderRepository repository;

    @KafkaListener(topics = "reversed-orders", groupId = "orders-group")
    public void reverseOrder(String event) {

        try {
            OrderEvent orderEvent = new ObjectMapper().readValue(event, OrderEvent.class);

            Optional<OrderEntity> order = this.repository.findById(orderEvent.getOrder().getOrderId());

            order.ifPresent(o -> {
                o.setStatus("FAILED");
                this.repository.save(o);
            });
        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}