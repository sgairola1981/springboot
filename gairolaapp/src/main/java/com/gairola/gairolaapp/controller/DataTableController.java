package com.gairola.gairolaapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gairola.gairolaapp.entity.Customer;
import com.gairola.gairolaapp.entity.Employee;
import com.gairola.gairolaapp.service.CustomerService;
import com.gairola.gairolaapp.service.EmployeeService;

@RestController
@RequestMapping("/datatable")
public class DataTableController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService service;

    @RequestMapping(path = "/employees", method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        System.out.println("*************employees calls");
        return employeeService.getAllEmployees();
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable("id") long id) {
        return employeeService.getEmployeeById(id);
    }

    @RequestMapping(path = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer() {
        System.out.println("*************employees calls");
        return service.getAllCustomer();
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("id") long id) {
        return service.getCustomerById(id);
    }
}
