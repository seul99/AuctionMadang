package com.auction.auctionapp.service;

import com.auction.auctionapp.domain.User;

import java.util.Optional;

public interface UserService {

    boolean login(String userId, String password);

    void signup(String userId, String password, String email);

    void updateProfile(String userId, String newEmail, String newPassword);
    void updateNickname(String userId, String nickname);

    void updateIntroduction(String userId, String intro);

    Optional<User> findByUserId(String userId);
    void updateProfileImage(String userId, String fileName);

    void deleteProfileImage(String userId);
    void updateEmail(String userId, String newEmail);
    void updatePassword(String userId, String newPassword);
    void updatePhone(String userId, String newPhoneNumber);
    void withdraw(String userId);


}
