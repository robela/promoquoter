package com.promoquoter.promoquoter.model;

import jakarta.persistence.*;

@Entity
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PromotionType type;

    private String category; // for PERCENT_OFF_CATEGORY
    private Integer percentOff; // for PERCENT_OFF_CATEGORY
    private Long productId; // for BUY_X_GET_Y
    private Integer buyX;
    private Integer getY;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public PromotionType getType() { return type; }
        public void setType(PromotionType type) { this.type = type; }
        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }
        public Integer getPercentOff() { return percentOff; }
        public void setPercentOff(Integer percentOff) { this.percentOff = percentOff; }
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public Integer getBuyX() { return buyX; }
        public void setBuyX(Integer buyX) { this.buyX = buyX; }
        public Integer getGetY() { return getY; }
        public void setGetY(Integer getY) { this.getY = getY; }
}
