package com.gairola.gairolaapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.gairola.gairolaapp.entity.Customer;

public interface CustomerService {

   List<Customer> getAllCustomer();

   void saveCustomer(Customer customer);

   Customer getCustomerById(long id);

   void deleteCustomerById(long id);

   Optional<Customer> getCustomerByIdOpt(long id);

   Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
