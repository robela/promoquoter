package com.promoquoter.promoquoter.repository;

import com.promoquoter.promoquoter.model.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
}
