package com.auction.auctionapp.repository;

import com.auction.auctionapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserIdAndPassword(String userId, String password);
    Optional<User> findByUserId(String userId);
    Optional<User> findByNickname(String nickname);
    boolean existsByUserIdAndIsDeletedFalse(String userId);

    boolean existsByNicknameAndIsDeletedFalse(String nickname);
    Optional<User> findByUserIdAndPasswordAndIsDeletedFalse(String userId, String password);


}
