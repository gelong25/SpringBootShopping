package com.example.demo.service;

import com.example.demo.dto.OrderRequestDto;
import com.example.demo.dto.OrderResponseDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Product product = productRepository.findById(orderRequestDto.getProductId()).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // 주문 저장
        Order order = new Order(product);
        orderRepository.save(order);
        return new OrderResponseDto(order);
    }

    // 주문 조회 : 선태 주문
    @Transactional()
    public OrderResponseDto getOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        return new OrderResponseDto(order);
    }
}
