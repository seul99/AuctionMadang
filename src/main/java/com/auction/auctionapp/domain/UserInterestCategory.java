package com.auction.auctionapp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserInterestCategory {

    @Id
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private String userId;

    @Id
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Integer categoryId;
}
