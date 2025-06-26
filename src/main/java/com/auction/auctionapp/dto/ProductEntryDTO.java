package com.auction.auctionapp.dto;

import com.auction.auctionapp.domain.enums.ProductCondition;
import com.auction.auctionapp.domain.enums.ProductStatus;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntryDTO {


    private Long productId;

    private String productName;

    private String productDescription;

    private BigDecimal productPrice;

    private String productImage;

    private ProductCondition productCondition;

    private Long categoryId;

    private ProductStatus productStatus;

    private LocalDate createDate;

    private MultipartFile imageFile;




}
