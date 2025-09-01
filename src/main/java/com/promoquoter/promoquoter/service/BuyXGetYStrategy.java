package com.promoquoter.promoquoter.service;

import com.promoquoter.promoquoter.model.Promotion;
import com.promoquoter.promoquoter.dto.CartQuoteResponse.LineItem;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BuyXGetYStrategy implements PromotionStrategy {
    @Override
    public void apply(List<LineItem> items, Promotion promotion) {
        if (promotion.getProductId() == null || promotion.getBuyX() == null || promotion.getGetY() == null) return;
        for (LineItem item : items) {
            if (promotion.getProductId().equals(item.getProductId())) {
                int freeItems = (item.getQty() / (promotion.getBuyX() + promotion.getGetY())) * promotion.getGetY();
                item.setDiscountedPrice(item.getPrice().multiply(java.math.BigDecimal.valueOf(item.getQty() - freeItems)));
            }
        }
    }
}
