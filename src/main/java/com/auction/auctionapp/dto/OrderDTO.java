package com.auction.auctionapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderDTO {
    private Long productId;
    private String name;
    private String zipcode;
    private String address;
    private String addressDetail;
    private String phone;
    private String email;

    private String productName;
    private BigDecimal Price;
}