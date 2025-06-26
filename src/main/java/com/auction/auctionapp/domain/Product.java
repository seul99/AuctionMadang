package com.auction.auctionapp.domain;

import com.auction.auctionapp.domain.enums.ProductCondition;
import com.auction.auctionapp.domain.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    //@ManyToOne
    //@JoinColumn(name = "seller_id")
    //private User seller;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;


    private LocalDate deadline;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "product_price", precision = 10, scale = 2)
    private BigDecimal productPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "image_path", length = 255)
    private String imagePath;

    @Enumerated(EnumType.STRING)
    @Column(name = "`condition`", length = 20)
    private ProductCondition condition;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int likeCount;

    public int getLikeCount() {
        return likeCount;
    }
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }
}