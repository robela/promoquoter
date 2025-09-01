package com.promoquoter.promoquoter.controller;

import com.promoquoter.promoquoter.model.Product;
import com.promoquoter.promoquoter.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Product", description = "Product management endpoints")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Operation(summary = "Create products", description = "Creates one or more products.")
    @PostMapping
    public ResponseEntity<List<Product>> createProducts(@RequestBody List<Product> products) {
        List<Product> saved = productRepository.saveAll(products);
        return ResponseEntity.ok(saved);
    }
}
