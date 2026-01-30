package com.example.demo.dto;

import com.example.demo.entity.Product;
import lombok.Getter;

@Getter
public class ProductResponseDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer stock;
    private String description;

    // Entity에서 DTO로 변환하는 생성자
    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.description = product.getDescription();
    }

}
