package com.auction.auctionapp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "`user`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname", unique = true)
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "address")
    private String address;

    @Column(name = "bank")
    private String bank;

    @Column(name = "category")
    private String category;

    @Column(name = "intro", columnDefinition = "TEXT")
    private String intro;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;




}
