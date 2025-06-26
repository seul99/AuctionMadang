package com.auction.auctionapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestId;

    // 사용자와 다대일 관계
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 상품과 다대일 관계
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}