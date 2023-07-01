package com.gairola.userservice.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;

import com.gairola.userservice.model.Product;
 
public interface ProductRepository extends JpaRepository<Product, Integer> {
 
}
