package com.promoquoter.promoquoter.service;

import com.promoquoter.promoquoter.model.Promotion;
import com.promoquoter.promoquoter.model.PromotionType;
import com.promoquoter.promoquoter.dto.CartQuoteResponse.LineItem;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PromotionEngine {
    public void applyPromotions(List<LineItem> items, List<Promotion> promotions) {
        for (Promotion promo : promotions) {
            PromotionStrategy strategy = getStrategy(promo.getType());
            if (strategy != null) {
                strategy.apply(items, promo);
            }
        }
    }

    private PromotionStrategy getStrategy(PromotionType type) {
        return switch (type) {
            case PERCENT_OFF_CATEGORY -> new PercentOffCategoryStrategy();
            case BUY_X_GET_Y -> new BuyXGetYStrategy();
        };
    }
}
