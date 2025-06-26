package com.auction.auctionapp.domain.enums;

public enum OrderStatus {
    PURCHASED("구매완료"),
    CANCELED("취소");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}