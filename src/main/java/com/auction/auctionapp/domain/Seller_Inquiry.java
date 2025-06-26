package com.auction.auctionapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Seller_Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sellerInquiryId;

    // 사용자와 다대일 관계 (작성자)
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String title;
    private String content;
    private LocalDateTime createdAt;
}
