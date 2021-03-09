package com.ugur.converter;

import com.ugur.dto.ProductDto;
import com.ugur.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product productDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setCategoryId(productDto.getCategoryId());
        return product;
    }

    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .imageUrl(product.getImageUrl())
                .categoryId(product.getCategoryId())
                .build();
    }
}
