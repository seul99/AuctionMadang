package com.auction.auctionapp.converter;

import com.auction.auctionapp.domain.Category;
import com.auction.auctionapp.domain.enums.ProductStatus;
import com.auction.auctionapp.repository.UserRepository;
import com.auction.auctionapp.repository.CategoryRepository;
import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.dto.ProductEntryDTO;
import com.auction.auctionapp.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ProductConverter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product toEntity(ProductEntryDTO dto, String userId) {

        //userId가 pk가 아니라서 findByUserId 사용함
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리를 찾을 수 없습니다."));


        return Product.builder()
                .name(dto.getProductName())
                .description(dto.getProductDescription())
                .productPrice(dto.getProductPrice())
                .imagePath(dto.getProductImage())
                .category(category)
                .status(ProductStatus.ON_SALE) // 기본값 세팅. 거래완료할때 변경
                .user(user)
                .condition(dto.getProductCondition())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
