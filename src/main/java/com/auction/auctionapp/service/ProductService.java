package com.auction.auctionapp.service;

import com.auction.auctionapp.domain.Product;
import com.auction.auctionapp.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    List<ProductListDTO> getProductsByCategoryId(Long categoryId);

    void productEntry(ProductEntryDTO dto, String userId);

    List<Product> searchProducts(String keyword);

    List<Product> findAllByUserId(String userId);

    DetailsPageDTO getProductDetails(Long productId);

    PurchaseCompleteDTO getPurchaseComplete(Long productId, String userId);




    void deleteProductById(Long productId);

    List<ProductListDTO> getProductList();
    Page<Product> findPagedByUserId(String userId, int page, int size);
    int countAllSales(Long sellerId);
    int countOnSale(Long sellerId);
    int countSoldOut(Long sellerId);
}

