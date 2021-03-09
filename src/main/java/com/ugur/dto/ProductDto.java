package com.ugur.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {
    private Integer id;
    private String name;
    private Integer quantity;
    private Double price;
    private String imageUrl;
    private Integer categoryId;
}
