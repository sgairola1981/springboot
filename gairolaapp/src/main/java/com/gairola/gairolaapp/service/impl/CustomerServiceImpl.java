package com.gairola.gairolaapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.gairola.gairolaapp.entity.Customer;
import com.gairola.gairolaapp.repository.CustomerRepository;
import com.gairola.gairolaapp.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repo;

    @Override
    public List<Customer> getAllCustomer() {
        return repo.findAll();
    }

    @Override
    public void saveCustomer(Customer entity) {
        this.repo.save(entity);
    }

    @Override
    public Customer getCustomerById(long id) {
        Optional<Customer> optional = repo.findById(id);
        Customer customer = null;
        if (optional.isPresent()) {
            customer = optional.get();
        } else {
            throw new RuntimeException(" Customer not found for id :: " + id);
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(long id) {
        this.repo.deleteById(id);
    }

    @Override
    public Page<Customer> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.repo.findAll(pageable);
    }

    public void saveOrUpdate(Customer customer) {
        repo.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerByIdOpt(long id) {
        return repo.findById(id);

    }
}