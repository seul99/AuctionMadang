package com.auction.auctionapp.service;

import com.auction.auctionapp.domain.Order;
import com.auction.auctionapp.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    void processOrder(Long productId, String userId, OrderDTO dto);
    List<Order> getOrderHistory(String userId);
    OrderDTO getOrderDTOByProductId(Long productId);


}