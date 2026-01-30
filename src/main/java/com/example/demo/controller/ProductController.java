package com.example.demo.controller;

import com.example.demo.dto.ProductRequestDto;
import com.example.demo.dto.ProductResponseDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    // 상품 등록
    @PostMapping
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    // 상품 목록 조회
    @GetMapping
    public List<ProductResponseDto> getProducts() {
        return productService.getProducts();
    }

    // 특정 상품 조회
    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    // 상품 삭제
    @DeleteMapping
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "successfully deleted";
    }
}
