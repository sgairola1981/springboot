package com.gairola.gairolaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gairola.gairolaapp.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}