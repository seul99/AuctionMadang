package com.auction.auctionapp.domain.enums;

public enum ProductCondition {
    NEW("새상품"),
    LIKE_NEW("사용감 없음"),
    LIGHTLY_USED("사용감 적음"),
    HEAVILY_USED("사용감 많음"),
    DAMAGED("고장/파손");

    private final String displayName;

    ProductCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}