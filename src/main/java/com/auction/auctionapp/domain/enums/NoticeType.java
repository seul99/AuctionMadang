package com.auction.auctionapp.domain.enums;

public enum NoticeType {
    NOTICE("공지"),
    EVENT("이벤트"),
    SERVICE_INFO("서비스 안내");

    private final String displayName;

    NoticeType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}