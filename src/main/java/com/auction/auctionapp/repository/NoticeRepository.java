package com.auction.auctionapp.repository;

import com.auction.auctionapp.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
