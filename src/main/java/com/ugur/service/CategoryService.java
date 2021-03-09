package com.ugur.service;

import com.ugur.dto.CategoryDto;
import com.ugur.exception.BusinessException;
import com.ugur.exception.EntityNotFoundExceptionById;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface CategoryService {

    CategoryDto create(CategoryDto categoryDto) throws ValidationException, BusinessException;

    void delete(Integer id) throws EntityNotFoundExceptionById;

    CategoryDto update(CategoryDto categoryDto) throws ValidationException, BusinessException;

    CategoryDto getCategoryByName(String name);

    CategoryDto getCategory(Integer id);

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getCategoryByParentCategoryIdEquals(Integer id);

}
