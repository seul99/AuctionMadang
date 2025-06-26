package com.auction.auctionapp.service.impl;

import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.repository.UserRepository;
import com.auction.auctionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean login(String userId, String password) {
        return true;
    }

    @Override
    public void signup(String userId, String password, String email) {
    }

    @Override
    public void updateProfile(String userId, String newEmail, String newPassword) {
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public void updateNickname(String userId, String nickname) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        user.setNickname(nickname);
        userRepository.save(user);
    }

    @Override
    public void updateIntroduction(String userId, String intro) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        user.setIntro(intro);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public void updateProfileImage(String userId, String fileName) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        user.setProfileImage("profileImages/" + fileName);
        userRepository.save(user);
    }

    @Override
    public void deleteProfileImage(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 없음"));
        user.setProfileImage(null);
        userRepository.save(user);
    }

    @Override
    public void updateEmail(String userId, String newEmail) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        user.setEmail(newEmail);
        userRepository.save(user);
    }

    @Override
    public void updatePassword(String userId, String newPassword) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    @Override
    public void updatePhone(String userId, String newPhoneNumber) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        user.setPhoneNumber(newPhoneNumber);
        userRepository.save(user);
    }
    @Override
    public void withdraw(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("유저 없음"));
        user.setIsDeleted(true);
        userRepository.save(user);
    }


}
