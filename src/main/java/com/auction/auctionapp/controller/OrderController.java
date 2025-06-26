package com.auction.auctionapp.controller;

import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.dto.DetailsPageDTO;
import com.auction.auctionapp.dto.OrderDTO;
import com.auction.auctionapp.dto.PurchaseCompleteDTO;
import com.auction.auctionapp.service.OrderService;
import com.auction.auctionapp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final ProductService productService;
    private final OrderService orderService;

    @GetMapping("{productId}/createorder")
    public String productPurchase(@PathVariable Long productId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("user", loginUser);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        if (loginUser == null) {
            return "Login/login";
        }

        DetailsPageDTO dto = productService.getProductDetails(productId);
        model.addAttribute("dto", dto);

        // 상품 상태가 SOLD_OUT이면 soldout 페이지로 이동 (createorder를 통해 넘어가는거 방지)
        if ("SOLD_OUT".equals(dto.getProductStatus())) {
            return "Order/soldout";
        }
        return "Order/productPurchase";
    }

    @PostMapping("/{productId}/purchaseComplete")
    public String purchaseComplete(@PathVariable("productId") long productId,
                                   @ModelAttribute OrderDTO dto,
                                   HttpSession session,
                                   Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("user", loginUser);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        if (loginUser == null) {
            return "Login/login";
        }

        String userId = loginUser.getUserId();

        //주문 처리
        orderService.processOrder(productId, userId, dto);

        PurchaseCompleteDTO completeDto = productService.getPurchaseComplete(productId, userId);
        model.addAttribute("dto", completeDto);
        return "Order/purchaseComplete";
    }

    //판매자 구매거래서 확인
    @GetMapping("{productId}/complete")
    public String ordercheck(@PathVariable("productId") long productId,
                             HttpSession session,
                             Model model) {
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "Login/login";
        }

        String userId = loginUser.getUserId();

        OrderDTO dto = orderService.getOrderDTOByProductId(productId);
        model.addAttribute("dto", dto);
        model.addAttribute("user", loginUser);

        return "Order/seller_purchase_details";
    }
}