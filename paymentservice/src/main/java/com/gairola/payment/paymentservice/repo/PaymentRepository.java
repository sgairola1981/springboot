package com.gairola.payment.paymentservice.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gairola.payment.paymentservice.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    public List<Payment> findByOrderId(long orderId);
}