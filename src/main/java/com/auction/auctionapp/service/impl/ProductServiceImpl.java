package com.auction.auctionapp.service.impl;

import com.auction.auctionapp.converter.ProductConverter;
import com.auction.auctionapp.domain.Order;
import com.auction.auctionapp.domain.Product;
import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.domain.enums.OrderStatus;
import com.auction.auctionapp.domain.enums.ProductStatus;
import com.auction.auctionapp.dto.*;
import com.auction.auctionapp.repository.OrderRepository;
import com.auction.auctionapp.repository.ProductRepository;
import com.auction.auctionapp.repository.UserRepository;
import com.auction.auctionapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void productEntry(ProductEntryDTO dto, String userId) {
        Product product = productConverter.toEntity(dto, userId);
        product.setCreatedAt(LocalDateTime.now()); // 이렇게 수정
        productRepository.save(product);
        System.out.println("상품 등록 완료: " + product.getName() + ", 등록자 ID: " + userId);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }
    //상품삭제
    @Override
    public void deleteProductById(Long productId) {
        List<Order> orders = orderRepository.findAllByProduct_ProductId(productId);
        orderRepository.deleteAll(orders);

        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductListDTO> getProductsByCategoryId(Long categoryId) {
        List<Product> products = productRepository.findByCategory_CategoryId(categoryId);
        return products.stream().map(product -> ProductListDTO.builder()
                .productId(product.getProductId())
                .productImage(product.getImagePath())
                .categoryName(product.getCategory().getCategoryName())
                .productName(product.getName())
                .productPrice(product.getProductPrice())
                .createdAt(product.getCreatedAt())
                .build()
        ).collect(Collectors.toList());
    }




    @Override
    public List<Product> findAllByUserId(String userId) {
        return productRepository.findAllByUserUserId(userId);
    }

    //구매자 상품 상세조회
    @Override
    public DetailsPageDTO getProductDetails(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        return DetailsPageDTO.builder()
                .productId(product.getProductId())
                .productName(product.getName())
                .productCondition(product.getCondition().getDisplayName())
                .productDescription(product.getDescription())
                .productImage(product.getImagePath())
                .productPrice(product.getProductPrice())
                .productStatus(product.getStatus().name())
                .build();
    }

    @Override
    public PurchaseCompleteDTO getPurchaseComplete(Long productId, String userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        com.auction.auctionapp.domain.User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        return PurchaseCompleteDTO.builder()
                .productImage(product.getImagePath())
                .productPrice(product.getProductPrice())
                .createdAt(product.getCreatedAt())
                .accountNo(product.getUser().getAccountNo())
                .bank(product.getUser().getBank())
                .build();
    }

    //상품리스트
    public List<ProductListDTO> getProductList() {
        List<Product> products = productRepository.findAll(); // 필요에 따라 정렬 등 가능

        return products.stream()
                .map(product -> ProductListDTO.builder()
                        .productId(product.getProductId())
                        .productImage(product.getImagePath())
                        .categoryName(product.getCategory().getCategoryName()) // 연관관계 주의
                        .productName(product.getName())
                        .productPrice(product.getProductPrice())
                        .createdAt(product.getCreatedAt())
                        .build()
                ).collect(Collectors.toList());
    }

    // 페이지
    @Override
    public Page<Product> findPagedByUserId(String userId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdAt").descending());
        return productRepository.findByUser_UserId(userId, pageable);
    }

    @Override
    public int countAllSales(Long sellerId) {
        return productRepository.countBySeller_UserNo(sellerId);
    }
    @Override
    public int countOnSale(Long sellerNo) {
        return productRepository.countBySeller_UserNo(sellerNo);
    }

    @Override
    public int countSoldOut(Long sellerNo) {
        return productRepository.countBySeller_UserNoAndStatus(sellerNo, ProductStatus.SOLD_OUT);
    }

}

