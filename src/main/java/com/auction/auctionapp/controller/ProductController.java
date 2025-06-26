package com.auction.auctionapp.controller;

import com.auction.auctionapp.domain.Product;
import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.dto.DetailsPageDTO;
import com.auction.auctionapp.dto.ProductEntryDTO;
import com.auction.auctionapp.dto.ProductListDTO;
import com.auction.auctionapp.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.auction.auctionapp.repository.UserRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final UserRepository userRepository;

    // 상품 등록 페이지로 이동
    @GetMapping("/createProduct")
    public String productEntry(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "Login/login";
        }
        model.addAttribute("isLoggedIn", true);
        return "Product/createProduct";
    }

    // 상품 등록 처리
    @PostMapping("/createProduct")
    public String productEntryComplete(@ModelAttribute ProductEntryDTO dto, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        String userId = loginUser.getUserId();

//        MultipartFile imageFile = dto.getImageFile();
//        if (imageFile != null && !imageFile.isEmpty()) {
//            String uploadDir = "C:/upload/images";
//            File dir = new File(uploadDir);
//            if (!dir.exists()) dir.mkdirs();
//
//            try {
//                String fileName = imageFile.getOriginalFilename();
//                File dest = new File(uploadDir + "/" + fileName);
//                imageFile.transferTo(dest);
//                dto.setProductImage("/images/" + fileName);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return "error-page";
//            }
//        } else {
//            dto.setProductImage(null); // 이미지 없으면 null
//        }
        MultipartFile imageFile = dto.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                // ━━ 저장 디렉토리 ━━━━━━━━━━━━━━━
                String rootPath   = new File("").getAbsolutePath();        // 프로젝트 루트
                String uploadDir  = rootPath + File.separator + "uploads" + File.separator + "images";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();                           // 최초 1회 생성

                // ━━ 파일명 중복 방지 ━━━━━━━━━━━━━
                String origName   = imageFile.getOriginalFilename();
                String ext        = origName.substring(origName.lastIndexOf("."));
                String safeName   = UUID.randomUUID() + ext;               // ex) a8f2...e.png

                // ━━ 실제 저장 ━━━━━━━━━━━━━━━━━━━
                File dest = new File(dir, safeName);
                imageFile.transferTo(dest);

                // DB/화면에 저장할 경로: "/images/uuid.png"
                dto.setProductImage(safeName);     // "uuid.png" 만 저장 (간결)
            } catch (IOException e) {
                e.printStackTrace();
                return "error-page";
            }
        } else {
            dto.setProductImage(null);             // 이미지 없으면 null
        }

        productService.productEntry(dto, userId); // 이미지 없어도 등록 진행
        return "redirect:/api/product/management";
    }


    // 상품 페이지 넘길 경우 페이지 다르게 보여주기 추가
    @GetMapping("/management")
    public String productManagement(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    Model model, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/api/user/login";
        }

        model.addAttribute("isLoggedIn", true);
        model.addAttribute("user", loginUser);

        // 페이징된 상품 리스트 조회
        Page<Product> productPage = productService.findPagedByUserId(loginUser.getUserId(), page, size);
        model.addAttribute("products", productPage.getContent()); // 실제 상품 목록
        model.addAttribute("currentPage", page);                     // 현재 페이지 번호
        model.addAttribute("totalPages", productPage.getTotalPages()); // 전체 페이지 수

        return "Product/ProductManagement";
    }



    // 구매자용 상품 상세 조회
    @GetMapping("/productDetailBuyer/{productId}")
    public String productDetailBuyer(@PathVariable Long productId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("user", loginUser);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        DetailsPageDTO dto = productService.getProductDetails(productId);
        model.addAttribute("dto", dto);
        return "Product/productDetailBuyer";
    }

    // 상품목록 조회하기
    @GetMapping("/productList")
    public String productList(@RequestParam(value = "category", required = false) String category,
                              @RequestParam(value = "categoryId", required = false) Long categoryId,
                              Model model, HttpSession session) {

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("user", loginUser);
        } else {
            model.addAttribute("isLoggedIn", false);
        }

        List<ProductListDTO> productList;


        if (categoryId != null) {
            productList = productService.getProductsByCategoryId(categoryId);
            model.addAttribute("selectedCategoryId", categoryId);
        }
        // 전체 상품
        else {
            productList = productService.getProductList();
        }

        model.addAttribute("productList", productList);
        return "Product/productList";
    }


    @PostMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/api/product/management"; // 삭제 후 상품 관리 페이지로 리디렉트
    }



}
