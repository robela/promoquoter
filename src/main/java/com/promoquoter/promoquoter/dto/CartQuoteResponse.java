package com.promoquoter.promoquoter.dto;

import java.math.BigDecimal;
import java.util.List;

public class CartQuoteResponse {
    private List<LineItem> items;
    private List<String> appliedPromotions;
    private BigDecimal total;

    public List<LineItem> getItems() { return items; }
    public void setItems(List<LineItem> items) { this.items = items; }
    public List<String> getAppliedPromotions() { return appliedPromotions; }
    public void setAppliedPromotions(List<String> appliedPromotions) { this.appliedPromotions = appliedPromotions; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public static class LineItem {
    private Long productId;
    private String name;
    private int qty;
    private BigDecimal price;
    private BigDecimal discountedPrice;

    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQty() { return qty; }
    public void setQty(int qty) { this.qty = qty; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public BigDecimal getDiscountedPrice() { return discountedPrice; }
    public void setDiscountedPrice(BigDecimal discountedPrice) { this.discountedPrice = discountedPrice; }
    }
}
