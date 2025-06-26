package com.auction.auctionapp.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseCompleteDTO {
    private Long productId;

    private String productImage;

    private BigDecimal productPrice;

    private LocalDateTime createdAt;

    private String accountNo;

    private String bank;
}