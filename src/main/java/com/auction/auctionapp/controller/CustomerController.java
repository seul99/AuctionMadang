package com.auction.auctionapp.controller;

import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // 1:1 문의 등록 (API 응답)
    @PostMapping("/inquiry")
    @ResponseBody
    public String createInquiry(@RequestParam String userId,
                                @RequestParam String title,
                                @RequestParam String content) {
        customerService.createInquiry(userId, title, content);
        return "문의가 등록되었습니다.";
    }

    // FAQ 목록 조회 (API 응답)
    @GetMapping("/faqs")
    @ResponseBody
    public String viewFAQList() {
        customerService.viewFAQList();
        return "FAQ 목록 반환 완료";
    }

    // 공지사항 페이지 렌더링
    @GetMapping("/notice")
    public String showNoticePage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("user", loginUser);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        return "customerCenter/notice"; // templates/customerCenter/notice.html
    }

}
