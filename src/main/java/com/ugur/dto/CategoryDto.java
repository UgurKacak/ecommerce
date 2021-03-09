package com.ugur.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Integer id;
    private Integer parentCategoryId;
    private String name;
    private String description;
}
