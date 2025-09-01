package com.promoquoter.promoquoter.repository;

import com.promoquoter.promoquoter.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByIdempotencyKey(String idempotencyKey);
}
