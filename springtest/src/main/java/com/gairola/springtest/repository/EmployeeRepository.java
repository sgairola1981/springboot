package com.gairola.springtest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gairola.springtest.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT e FROM Employee e  WHERE e.email = ?1")
    Optional<Employee> findByEmail(String email);

    @Query(value = "SELECT e FROM Employee e  WHERE e.email = ?1")
    Optional<Employee> findAll(String email);

}
