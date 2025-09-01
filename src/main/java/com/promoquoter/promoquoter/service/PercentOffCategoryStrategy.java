package com.promoquoter.promoquoter.service;

import com.promoquoter.promoquoter.model.Promotion;
import com.promoquoter.promoquoter.dto.CartQuoteResponse.LineItem;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PercentOffCategoryStrategy implements PromotionStrategy {
    @Override
    public void apply(List<LineItem> items, Promotion promotion) {
        if (promotion.getCategory() == null || promotion.getPercentOff() == null) return;
        for (LineItem item : items) {
            if (promotion.getCategory().equals(item.getName())) {
                BigDecimal discount = item.getPrice().multiply(BigDecimal.valueOf(promotion.getPercentOff()).divide(BigDecimal.valueOf(100)));
                item.setDiscountedPrice(item.getPrice().subtract(discount));
            }
        }
    }
}
