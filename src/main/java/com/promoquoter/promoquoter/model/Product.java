package com.promoquoter.promoquoter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @DecimalMin("0.00")
    private BigDecimal price;

    @Min(0)
    private int stock;

    @Version
    private Long version;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public Long getVersion() { return version; }
    public void setVersion(Long version) { this.version = version; }
}
