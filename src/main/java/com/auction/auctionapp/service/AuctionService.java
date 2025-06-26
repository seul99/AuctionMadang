package com.auction.auctionapp.service;

public interface AuctionService {

    void registerAuction(Long productId, int startingPrice, String sellerId);

    void placeBid(Long auctionId, String bidderId, int bidAmount);

    void closeAuction(Long auctionId);
}
