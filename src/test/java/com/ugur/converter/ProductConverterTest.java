package com.ugur.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ugur.dto.ProductDto;
import com.ugur.entity.Product;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductConverterTest {

    private ProductConverter productConverter;

    @BeforeEach
    void setUp() {
        productConverter = new ProductConverter();
    }

    @Test
    void productDtoToProduct() {
        ProductDto productDto = ProductDto.builder()
                .id(1)
                .name("Sample Product")
                .quantity(2)
                .price(21.99)
                .imageUrl("http://image-url.com")
                .categoryId(4)
                .build();

        Product product = productConverter.productDtoToProduct(productDto);

        //assertThat(productDto.hashCode()).isEqualTo(product.hashCode());
        assertThat(productDto.getId()).isEqualTo(product.getId());
        assertThat(productDto.getName()).isEqualTo(product.getName());
        assertThat(productDto.getQuantity()).isEqualTo(product.getQuantity());
        assertThat(productDto.getPrice()).isEqualTo(product.getPrice());
        assertThat(productDto.getImageUrl()).isEqualTo(product.getImageUrl());
        assertThat(productDto.getCategoryId()).isEqualTo(product.getCategoryId());
    }

    @Test
    void productToProductDto() {
        Product product = new Product(1, "Sample Product", 2, 21.99, "http://image-url.com", 4);
        ProductDto productDto = productConverter.productToProductDto(product);

        //assertThat(product.hashCode()).isEqualTo(productDto.hashCode());
        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getQuantity()).isEqualTo(productDto.getQuantity());
        assertThat(product.getPrice()).isEqualTo(productDto.getPrice());
        assertThat(product.getImageUrl()).isEqualTo(productDto.getImageUrl());
        assertThat(product.getCategoryId()).isEqualTo(productDto.getCategoryId());
    }
}