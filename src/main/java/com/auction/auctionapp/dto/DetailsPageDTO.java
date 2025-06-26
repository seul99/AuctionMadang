package com.auction.auctionapp.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetailsPageDTO {


    private Long productId;

    private String productName;

    private String productDescription;

    private String productImage;

    private BigDecimal productPrice;

    private String productStatus;

    private String productCondition;
}
