package com.promoquoter.promoquoter.service;
import com.promoquoter.promoquoter.model.Promotion;
import com.promoquoter.promoquoter.dto.CartQuoteResponse.LineItem;
import java.util.List;

public interface PromotionStrategy {
    void apply(List<LineItem> items, Promotion promotion);
}
