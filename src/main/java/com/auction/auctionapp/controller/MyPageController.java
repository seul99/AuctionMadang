package com.auction.auctionapp.controller;

import com.auction.auctionapp.domain.Order;
import com.auction.auctionapp.domain.Product;
import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.repository.UserRepository;
import com.auction.auctionapp.service.OrderService;
import com.auction.auctionapp.service.ProductService;
import com.auction.auctionapp.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/user")
public class MyPageController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;



    @GetMapping("/myPage")
    public String myPage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }

        model.addAttribute("isLoggedIn", true);
        model.addAttribute("user", loginUser);

        // 판매 내역 개수 조회
        Long sellerNo = loginUser.getUserNo();
        int total = productService.countAllSales(sellerNo);
        int onSale = productService.countOnSale(sellerNo);
        int soldOut = productService.countSoldOut(sellerNo);

        model.addAttribute("saleAll", total);
        model.addAttribute("saleOn", onSale);
        model.addAttribute("saleOff", soldOut);

        return "MyPage/myPage";
    }


    @GetMapping("/{userId}/purchaseList")
    public String orderHistory(@PathVariable String userId, Model model, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }
        model.addAttribute("isLoggedIn", true);
        if (!loginUser.getUserId().equals(userId)) {
            return "MyPage/accessDenied";
        }

        List<Order> orders = orderService.getOrderHistory(userId);
        model.addAttribute("orders", orders);
        model.addAttribute("user", loginUser);

        return "MyPage/purchasesList";
    }

    @GetMapping("/{userId}/salesList")
    public String salesList(@PathVariable String userId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }
        model.addAttribute("isLoggedIn", true);
        if (!loginUser.getUserId().equals(userId)) {
            return "MyPage/accessDenied";
        }

        List<Product> products = productService.findAllByUserId(userId);
        model.addAttribute("products", products);
        model.addAttribute("user", loginUser);

        return "MyPage/salesList";
    }

    // 프로필수정
    @GetMapping("/{userId}/edit-profile")
    public String editProfilePage(@PathVariable String userId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null || !loginUser.getUserId().equals(userId)) {
            return "Login/login";
        }

        model.addAttribute("user", loginUser);
        model.addAttribute("isLoggedIn", true);
        return "MyPage/edit-profile";
    }

    // 닉네임 수정
    @PostMapping("/updateNickname")
    public String updateNickname(@RequestParam String nickname, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }

        userService.updateNickname(loginUser.getUserId(), nickname);

        loginUser.setNickname(nickname); // 세션도 반영
        session.setAttribute("loginUser", loginUser);

        return "redirect:/api/user/" + loginUser.getUserId() + "/edit-profile";
    }

    // 소개글 수정
    @PostMapping("/updateIntroduction")
    public String updateIntroduction(@RequestParam String intro, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }

        userService.updateIntroduction(loginUser.getUserId(), intro);

        loginUser.setIntro(intro); // 세션도 반영
        session.setAttribute("loginUser", loginUser);

        return "redirect:/api/user/" + loginUser.getUserId() + "/edit-profile";
    }


    //프로필사진 수정하기
    @PostMapping("/updateProfileImage")
    public String updateProfileImage(@RequestParam("profileImage") MultipartFile file,
                                     HttpSession session) throws IOException {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }

        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            // uploads/profileImages
            String uploadPath = new File("uploads/profileImages").getAbsolutePath();
            File saveFile = new File(uploadPath, originalFilename);
            file.transferTo(saveFile);

            userService.updateProfileImage(loginUser.getUserId(), originalFilename);

            // 세션 반영
            loginUser.setProfileImage("profileImages/" + originalFilename);
            session.setAttribute("loginUser", loginUser);
        }

        return "redirect:/api/user/" + loginUser.getUserId() + "/edit-profile";
    }


    // 프로필 삭제
    @PostMapping("/deleteProfileImage")
    public String deleteProfileImage(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }

        // 실제 파일 삭제
        String profileImage = loginUser.getProfileImage(); // 예: "profileImages/seulgi.png"
        if (profileImage != null) {
            File deleteFile = new File("uploads/" + profileImage);
            if (deleteFile.exists()) {
                deleteFile.delete();
            }
        }

        // DB 반영
        userService.deleteProfileImage(loginUser.getUserId());

        // 세션 반영
        loginUser.setProfileImage(null);
        session.setAttribute("loginUser", loginUser);

        return "redirect:/api/user/" + loginUser.getUserId() + "/edit-profile";
    }

    //로그인정보확인하기
    @GetMapping("/{userId}/login-detail")
    public String loginDetailPage(@PathVariable String userId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null || !loginUser.getUserId().equals(userId)) {
            return "Login/login";
        }

        model.addAttribute("user", loginUser);
        model.addAttribute("isLoggedIn", true);
        return "MyPage/login-detail";
    }

    //로그인정보 수정하기

    //이메일변경
    @PostMapping("/email/update")
    @ResponseBody
    public ResponseEntity<String> updateEmail(@RequestBody Map<String, String> data) {
        String userId = data.get("userId");
        String newEmail = data.get("newEmail");

        userService.updateEmail(userId, newEmail);
        return ResponseEntity.ok("updated");
    }

    //비밀번호변경
    @PostMapping("/password/update")
    @ResponseBody
    public ResponseEntity<String> updatePassword(@RequestBody Map<String, String> data) {
        String userId = data.get("userId");
        String newPassword = data.get("newPassword");

        userService.updatePassword(userId, newPassword);
        return ResponseEntity.ok("password updated");
    }

    //핸드폰번호 변경
    @PostMapping("/phone/update")
    @ResponseBody
    public ResponseEntity<String> updatePhone(@RequestBody Map<String, String> data) {
        String userId = data.get("userId");
        String newPhoneNumber = data.get("newPhoneNumber");

        userService.updatePhone(userId, newPhoneNumber);
        return ResponseEntity.ok("phone updated");
    }

    //회원탈퇴
    @PostMapping("/withdraw")
    public String withdraw(@RequestParam String userId, HttpSession session) {
        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            user.setNickname(user.getNickname() + "_deleted_" + UUID.randomUUID().toString().substring(0, 6));
            user.setUserId(user.getUserId() + "_deleted_" + UUID.randomUUID().toString().substring(0, 6));
            user.setIsDeleted(true);

            userRepository.save(user);
            session.invalidate(); // 세션 삭제
        }

        return "redirect:/"; // 홈으로 이동
    }




}