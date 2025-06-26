package com.auction.auctionapp.domain.enums;

import lombok.Getter;

@Getter
public enum ProductStatus {
    ON_SALE("판매중"),
    SOLD_OUT("판매완료");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}