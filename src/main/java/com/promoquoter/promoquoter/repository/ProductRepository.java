package com.promoquoter.promoquoter.repository;

import com.promoquoter.promoquoter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
