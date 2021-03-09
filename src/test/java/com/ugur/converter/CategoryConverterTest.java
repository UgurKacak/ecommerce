package com.ugur.converter;

import com.ugur.dto.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ugur.entity.Category;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("squid:S5786")
public class CategoryConverterTest {

    private CategoryConverter categoryConverter;
    @BeforeEach
    void setUp() {
        categoryConverter = new CategoryConverter();
    }

    @Test
    void categoryDtoToCategory() {
        CategoryDto categoryDto = CategoryDto.builder()
                .id(4)
                .parentCategoryId(2)
                .name("Test Category")
                .description("Test Category Description")
                .build();

        Category category = categoryConverter.categoryDtoToCategory(categoryDto);

        //assertThat(categoryDto.hashCode()).isEqualTo(category.hashCode());
        assertThat(categoryDto.getId()).isEqualTo(category.getId());
        assertThat(categoryDto.getParentCategoryId()).isEqualTo(category.getParentCategoryId());
        assertThat(categoryDto.getName()).isEqualTo(category.getName());
        assertThat(categoryDto.getDescription()).isEqualTo(category.getDescription());
    }

    @Test
    void categoryToCategoryDto() {
        Category category = new Category(1, 2, "Test Category", "Test Category Description");
        CategoryDto categoryDto = categoryConverter.categoryToCategoryDto(category);

        //assertThat(category.hashCode()).isEqualTo(categoryDto.hashCode());
        assertThat(category.getId()).isEqualTo(categoryDto.getId());
        assertThat(category.getParentCategoryId()).isEqualTo(categoryDto.getParentCategoryId());
        assertThat(category.getName()).isEqualTo(categoryDto.getName());
        assertThat(category.getDescription()).isEqualTo(categoryDto.getDescription());

    }
}