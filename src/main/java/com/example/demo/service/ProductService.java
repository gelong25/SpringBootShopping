package com.example.demo.service;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    // 상품 등록
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto productRequestDto) {
        Product product = new Product(productRequestDto.getName(), productRequestDto.getPrice(), productRequestDto.getStock(), productRequestDto.getDescription());
        productRepository.save(product);
        return new ProductResponseDto(product);
    }

    // 상품 조회 : 전체 상품
    public List<ProductResponseDto> getProducts() {
        return productRepository.findAll().stream().map(product -> new ProductResponseDto(product)).collect(Collectors.toList());
    }

    // 상품 조회 : 선택 상품
    // 상품 조회 불가능한 경우 : 예외 처리
    public ProductResponseDto getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return new ProductResponseDto(product);
    }

    // 상품 정보 수정
    // 상품 조회 불가능한 경우 : 예외 처리
    @Transactional
    public ProductResponseDto updateProduct(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));

        // 트랜잭션 끝나면 자동 업데이트
        product.update(productRequestDto.getName(), product.getPrice(), product.getStock(), product.getDescription());
        return new ProductResponseDto(product);
    }

    // 상품 삭제
    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
