package com.auction.auctionapp.service.impl;

import com.auction.auctionapp.domain.Order;
import com.auction.auctionapp.domain.Product;
import com.auction.auctionapp.domain.User;
import com.auction.auctionapp.domain.enums.OrderStatus;
import com.auction.auctionapp.domain.enums.ProductStatus;
import com.auction.auctionapp.dto.OrderDTO;
import com.auction.auctionapp.repository.OrderRepository;
import com.auction.auctionapp.repository.ProductRepository;
import com.auction.auctionapp.repository.UserRepository;
import com.auction.auctionapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

//    @Override
//    public void createOrder(Long auctionId, String buyerId) {
//        // TODO: 주문 생성 로직
//        System.out.println("주문 생성: 경매 ID = " + auctionId + ", 구매자 ID = " + buyerId);
//    }

    @Override
    public List<Order> getOrderHistory(String userId){
        return orderRepository.findAllByBuyer_UserId(userId);
    }

    @Override
    public void processOrder(Long productId, String userId, OrderDTO dto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다."));

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        BigDecimal totalPrice = product.getProductPrice().add(new BigDecimal("4000"));

        Order order = new Order();
        order.setProduct(product);
        order.setBuyer(user);
        order.setOrderStatus(OrderStatus.PURCHASED);
        order.setShippingAddress(dto.getZipcode() + " " + dto.getAddress() + " " + dto.getAddressDetail());
        order.setReceiverName(dto.getName());
        order.setReceiverPhone(dto.getPhone());
        order.setTotalPrice(totalPrice);
        order.setOrderAt(LocalDateTime.now());

        product.setStatus(ProductStatus.SOLD_OUT);

        orderRepository.save(order);
        productRepository.save(product);
    }

    public OrderDTO getOrderDTOByProductId(Long productId) {
        Order order = orderRepository.findByProduct_ProductId(productId)
                .orElseThrow(() -> new IllegalArgumentException("주문 내역이 없습니다."));

        Product product = order.getProduct();

        OrderDTO dto = new OrderDTO();
        dto.setProductId(productId);
        dto.setName(order.getReceiverName());
        dto.setPhone(order.getReceiverPhone());

        dto.setProductName(product.getName());
        dto.setPrice(BigDecimal.valueOf(product.getProductPrice().intValue()));

        // 주소는 Order 엔티티에서 배송주소 통합 필드로 관리하므로 적절히 나눠야 함 (임시)
        String[] addressParts = order.getShippingAddress().split(" ", 3);
        if (addressParts.length >= 3) {
            dto.setZipcode(addressParts[0]);
            dto.setAddress(addressParts[1]);
            dto.setAddressDetail(addressParts[2]);
        }

        // email은 Order에 없으므로 Buyer에서 가져옴
        dto.setEmail(order.getBuyer().getEmail());

        return dto;
    }
}