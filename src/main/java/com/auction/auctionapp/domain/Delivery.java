package com.auction.auctionapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;

    // 주문과 일대일 관계
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private String trackingNumber;
    private String deliveryStatus;
}
