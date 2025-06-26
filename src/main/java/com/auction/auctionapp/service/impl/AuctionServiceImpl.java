package com.auction.auctionapp.service.impl;

import com.auction.auctionapp.service.AuctionService;
import org.springframework.stereotype.Service;


@Service
public class AuctionServiceImpl implements AuctionService {


    public void registerAuction(Long productId, int startingPrice, String sellerId) {
        // TODO: 경매 등록 로직
        System.out.println("경매 등록: 상품 ID = " + productId);
    }


    public void placeBid(Long auctionId, String bidderId, int bidAmount) {
        // TODO: 입찰 로직
        System.out.println("입찰: 경매 ID = " + auctionId + ", 입찰자 ID = " + bidderId);
    }


    public void closeAuction(Long auctionId) {
        // TODO: 경매 종료 로직
        System.out.println("경매 종료: 경매 ID = " + auctionId);
    }
}
