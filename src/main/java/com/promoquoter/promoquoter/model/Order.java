package com.promoquoter.promoquoter.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    private String idempotencyKey;
    @ElementCollection
    private List<Long> productIds;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getIdempotencyKey() { return idempotencyKey; }
    public void setIdempotencyKey(String idempotencyKey) { this.idempotencyKey = idempotencyKey; }
    public List<Long> getProductIds() { return productIds; }
    public void setProductIds(List<Long> productIds) { this.productIds = productIds; }
}
