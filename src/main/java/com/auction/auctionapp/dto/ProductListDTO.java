package com.auction.auctionapp.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductListDTO{
    private Long productId;
    private String productImage;
    private String categoryName;
    private String productName;
    private BigDecimal productPrice;
    private LocalDateTime createdAt;
    private MultipartFile imageFile;
}