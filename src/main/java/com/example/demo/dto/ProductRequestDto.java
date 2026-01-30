package com.example.demo.dto;

import lombok.Getter;

@Getter
// 상품 등록 요청을 위한 DTO
public class ProductRequestDto {
    private String name;
    private Integer price;
    private Integer stock;
    private String description;
}
