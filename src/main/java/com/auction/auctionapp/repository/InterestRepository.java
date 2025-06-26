package com.auction.auctionapp.repository;

import com.auction.auctionapp.domain.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
