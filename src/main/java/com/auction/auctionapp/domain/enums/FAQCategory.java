package com.auction.auctionapp.domain.enums;

public enum FAQCategory {
    POLICY("이용정책"),
    PURCHASE("구매"),
    SALE("판매");

    private final String displayName;

    FAQCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
