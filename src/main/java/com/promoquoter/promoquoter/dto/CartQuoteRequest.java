package com.promoquoter.promoquoter.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CartQuoteRequest {
    @NotNull
    private List<CartItemDto> items;
    private String customerSegment;

    public List<CartItemDto> getItems() { return items; }
    public void setItems(List<CartItemDto> items) { this.items = items; }
    public String getCustomerSegment() { return customerSegment; }
    public void setCustomerSegment(String customerSegment) { this.customerSegment = customerSegment; }
}
