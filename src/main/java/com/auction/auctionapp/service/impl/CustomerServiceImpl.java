package com.auction.auctionapp.service.impl;

import com.auction.auctionapp.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public void createInquiry(String userId, String title, String content) {
        // TODO: 1:1 문의 작성 로직
        System.out.println("1:1 문의 작성: 사용자 ID = " + userId);
    }

    @Override
    public void viewFAQList() {
        // TODO: FAQ 목록 조회 로직
        System.out.println("FAQ 목록 조회");
    }

    @Override
    public void viewNoticeList() {
        // TODO: 공지사항 목록 조회 로직
        System.out.println("공지사항 목록 조회");
    }
}
