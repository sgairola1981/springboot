package com.gairola.springbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gairola.springbatch.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}