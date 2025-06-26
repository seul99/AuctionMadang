package com.auction.auctionapp.controller;

import com.auction.auctionapp.domain.Order;
import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.repository.UserRepository;
import com.auction.auctionapp.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("api/user/")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // 회원가입 폼 페이지
    @GetMapping("/join")
    public String showJoin() {
        return "Login/join";
    }

    // 로그인 폼 페이지
    @GetMapping("/login")
    public String showLogin() {
        return "Login/login";
    }

    // 회원가입 처리 POST 요청
    @PostMapping("/join")
    public String registerCustomer(@ModelAttribute User user, Model model) {
        boolean existsUserId = userRepository.existsByUserIdAndIsDeletedFalse(user.getUserId());
        boolean existsNickname = userRepository.existsByNicknameAndIsDeletedFalse(user.getNickname());

        if (existsUserId || existsNickname) {
            model.addAttribute("error", "아이디 또는 닉네임이 이미 사용 중입니다.");
            return "Login/join";
        }

        userRepository.save(user);
        return "redirect:/api/user/join?success=true";
    }


    // 로그인 처리
    @PostMapping("/login")
    public String doLogin(@RequestParam String id,
                          @RequestParam String password,
                          Model model,
                          HttpSession session) {


        Optional<User> user = userRepository.findByUserIdAndPasswordAndIsDeletedFalse(id, password);


        if (user.isPresent()) {
            session.setAttribute("loginUser", user.get());
            model.addAttribute("nickname", user.get().getNickname());
            return "redirect:/";
        } else {
            model.addAttribute("loginError", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "Login/login";
        }
    }

    //로그아웃처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/api/user/login";
    }

    // 마이페이지 이동
    public String myPage() {
        return "MyPage/myPage"; //
    }


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/check-id")   // ➜ 최종 경로:  /api/user/check-id
    public ResponseEntity<Map<String, Boolean>> checkDuplicateId(@RequestParam String userId) {
        boolean exists = userRepository.existsByUserIdAndIsDeletedFalse(userId);
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Map<String, Boolean>> checkDuplicateNickname(@RequestParam String nickname) {
        boolean exists = userRepository.existsByNicknameAndIsDeletedFalse(nickname);

        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

}
