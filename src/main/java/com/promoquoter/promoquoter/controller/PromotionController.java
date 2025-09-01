package com.promoquoter.promoquoter.controller;

import com.promoquoter.promoquoter.model.Promotion;
import com.promoquoter.promoquoter.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

@Tag(name = "Promotion", description = "Promotion management endpoints")
@RestController
@RequestMapping("/promotions")
public class PromotionController {
    @Autowired
    private PromotionRepository promotionRepository;

    @Operation(summary = "Create promotions", description = "Creates one or more promotions.")
    @PostMapping
    public ResponseEntity<List<Promotion>> createPromotions(@RequestBody List<Promotion> promotions) {
        List<Promotion> saved = promotionRepository.saveAll(promotions);
        return ResponseEntity.ok(saved);
    }
}
