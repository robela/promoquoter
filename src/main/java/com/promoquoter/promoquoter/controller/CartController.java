package com.promoquoter.promoquoter.controller;

import com.promoquoter.promoquoter.dto.CartQuoteRequest;
import com.promoquoter.promoquoter.dto.CartQuoteResponse;
import com.promoquoter.promoquoter.dto.CartQuoteResponse.LineItem;
import com.promoquoter.promoquoter.model.Product;
import com.promoquoter.promoquoter.model.Promotion;
import com.promoquoter.promoquoter.repository.ProductRepository;
import com.promoquoter.promoquoter.repository.PromotionRepository;
import com.promoquoter.promoquoter.service.PromotionEngine;
import com.promoquoter.promoquoter.model.Order;
import com.promoquoter.promoquoter.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
@Tag(name = "Cart", description = "Cart operations for quoting and confirming orders")
public class CartController {
    @Autowired
    private OrderRepository orderRepository;
    @PostMapping("/confirm")
        @Operation(summary = "Confirm cart and reserve inventory", description = "Validates stock, reserves items, and creates an order.")
        public ResponseEntity<?> confirm(@RequestBody CartQuoteRequest request, @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        if (idempotencyKey != null && orderRepository.findByIdempotencyKey(idempotencyKey).isPresent()) {
            return ResponseEntity.ok(orderRepository.findByIdempotencyKey(idempotencyKey).get());
        }
        List<Long> reservedProductIds = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        for (var itemDto : request.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId()).orElse(null);
            if (product == null || product.getStock() < itemDto.getQty()) {
                return ResponseEntity.status(409).body("Insufficient stock for productId: " + itemDto.getProductId());
            }
            product.setStock(product.getStock() - itemDto.getQty());
            productRepository.save(product);
            reservedProductIds.add(product.getId());
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(itemDto.getQty())));
        }
        Order order = new Order();
        order.setTotal(total);
        order.setIdempotencyKey(idempotencyKey);
        order.setProductIds(reservedProductIds);
        orderRepository.save(order);
        return ResponseEntity.ok(order);
    }
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private PromotionEngine promotionEngine;

    @PostMapping("/quote")
        @Operation(summary = "Get cart price quote", description = "Calculates the final price and applies promotions.")
        public ResponseEntity<CartQuoteResponse> quote(@RequestBody CartQuoteRequest request) {
        List<LineItem> lineItems = new ArrayList<>();
        for (var itemDto : request.getItems()) {
            Product product = productRepository.findById(itemDto.getProductId()).orElse(null);
            if (product == null) continue;
            LineItem line = new LineItem();
            line.setProductId(product.getId());
            line.setName(product.getName());
            line.setQty(itemDto.getQty());
            line.setPrice(product.getPrice());
            line.setDiscountedPrice(product.getPrice());
            lineItems.add(line);
        }
        List<Promotion> promotions = promotionRepository.findAll();
        promotionEngine.applyPromotions(lineItems, promotions);
        BigDecimal total = lineItems.stream()
            .map(li -> li.getDiscountedPrice() != null ? li.getDiscountedPrice() : li.getPrice())
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        CartQuoteResponse response = new CartQuoteResponse();
        response.setItems(lineItems);
        response.setAppliedPromotions(new ArrayList<>()); // Fill with applied promotions if needed
        response.setTotal(total);
        return ResponseEntity.ok(response);
    }
}
