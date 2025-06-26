package com.auction.auctionapp.controller;

import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.dto.CategoryDTO;
import com.auction.auctionapp.dto.HotItemDTO;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showMain(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        model.addAttribute("isLoggedIn", loginUser != null);
        model.addAttribute("nickname", loginUser != null ? loginUser.getNickname() : null);

        return "home/main";  //
    }

    // 마이페이지 이동
    @GetMapping("/mypage")
    public String goToMyPage() {
        return "MyPage/myPage";  // templates/MyPage/maPage.html
    }

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model) {
        // 세션에서 로그인 유저 가져오기
        User loginUser = (User) session.getAttribute("loginUser");

        // 카테고리 리스트
        List<CategoryDTO> categoryList = List.of(
                new CategoryDTO("/images/cate1.png", "디지털 기기"),
                new CategoryDTO("/images/cate2.png", "가구/인테리어"),
                new CategoryDTO("/images/cate3.png", "유아동 물품"),
                new CategoryDTO("/images/cate4.png", "여성의류"),
                new CategoryDTO("/images/cate5.png", "여성잡화"),
                new CategoryDTO("/images/cate6.png", "남성패션/잡화"),
                new CategoryDTO("/images/cate7.png", "생활가전"),
                new CategoryDTO("/images/cate8.png", "취미/게임/음반"),
                new CategoryDTO("/images/cate9.png", "뷰티/미용"),
                new CategoryDTO("/images/cate10.png", "더보기")
        );

        // 추천 상품 리스트
        List<HotItemDTO> hotItemList = List.of(
                new HotItemDTO("/images/hot01.jpg", "나이키 후드티", "의류", "신품급 상태", "100,000원", "2025-06-30"),
                new HotItemDTO("/images/hot02.jpg", "나이키 바람막이", "의류", "신품급 상태", "30,000원", "2025-07-01"),
                new HotItemDTO("/images/hot03.jpg", "아디다스 져지", "의류", "신품급 상태", "30,000원", "2025-07-01"),
                new HotItemDTO("/images/hot04.jpg", "아디다스 바람막이", "의류", "신품급 상태", "30,000원", "2025-07-01"),
                new HotItemDTO("/images/hot05.jpg", "구찌 져지", "의류", "신품급 상태", "30,000원", "2025-07-01")
        );

        // 모델에 추가
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("hotItemList", hotItemList);
        model.addAttribute("isLoggedIn", loginUser != null);
        model.addAttribute("nickname", loginUser != null ? loginUser.getNickname() : null);

        return "home/main";
    }

}
