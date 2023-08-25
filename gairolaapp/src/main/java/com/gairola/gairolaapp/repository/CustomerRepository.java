package com.gairola.gairolaapp.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.gairola.gairolaapp.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}