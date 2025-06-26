package com.auction.auctionapp.repository;

import com.auction.auctionapp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByBuyer_UserId(String userId);
    Optional<Order> findByProduct_ProductId(Long productId);
    List<Order> findAllByProduct_ProductId(Long productId);



}
