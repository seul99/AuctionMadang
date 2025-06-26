package com.auction.auctionapp.service;

public interface CustomerService {

    void createInquiry(String userId, String title, String content);

    void viewFAQList();

    void viewNoticeList();
}
