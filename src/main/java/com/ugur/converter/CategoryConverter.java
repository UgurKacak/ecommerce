package com.ugur.converter;

import com.ugur.dto.CategoryDto;
import com.ugur.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setParentCategoryId(categoryDto.getParentCategoryId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return category;
    }

    public CategoryDto categoryToCategoryDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .parentCategoryId(category.getParentCategoryId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
