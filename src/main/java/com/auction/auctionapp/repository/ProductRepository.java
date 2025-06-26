package com.auction.auctionapp.repository;

import com.auction.auctionapp.domain.Product;
import com.auction.auctionapp.domain.enums.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory_CategoryId(Long categoryId);



    Optional<Product> findByName(String name);
    List<Product> findByNameContainingIgnoreCase(String keyword);
    List<Product> findAllByUserUserId(String userId);
    Page<Product> findByUser_UserId(String userId, Pageable pageable);
    // 전체 판매 수
    int countBySeller_UserNo(Long userNo);

    // 상태별 판매 수
    int countBySeller_UserNoAndStatus(Long userNo, ProductStatus status);
}