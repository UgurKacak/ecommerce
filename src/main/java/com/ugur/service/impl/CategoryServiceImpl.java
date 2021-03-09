package com.ugur.service.impl;

import com.ugur.converter.CategoryConverter;
import com.ugur.dto.CategoryDto;
import com.ugur.exception.BusinessException;
import com.ugur.repository.CategoryRepository;
import com.ugur.service.CategoryService;
import com.ugur.entity.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ugur.exception.EntityNotFoundExceptionById;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    @Override
    public CategoryDto create(CategoryDto categoryDto) throws BusinessException, ValidationException {
        validateCategoryDto(categoryDto);
        Category category = categoryRepository.getCategoryByName(categoryDto.getName());

        if (category != null) {
            throw new BusinessException("Category: " + categoryDto.getName() + " is already exist.");
        }

        Category createdCategory = categoryRepository.save(categoryConverter.categoryDtoToCategory(categoryDto));
        log.info("Category create operation successful: " + createdCategory.getName());
        return categoryConverter.categoryToCategoryDto(createdCategory);
    }

    @Override
    public void delete(Integer id) throws EntityNotFoundExceptionById {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundExceptionById("Invalid id for delete operation | id:" + id);
        }
       categoryRepository.deleteById(id);
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) throws BusinessException, ValidationException {
        validateCategoryDto(categoryDto);

        Category category = categoryRepository.getCategoryById(categoryDto.getId());

        if (category == null) {
            throw new BusinessException("Category id: " + categoryDto.getId() + " is not exist.");
        }

        Category updatedCategory = categoryRepository.save(categoryConverter.categoryDtoToCategory(categoryDto));
        log.info("Category update operation successful: " + updatedCategory.getName());
        return categoryConverter.categoryToCategoryDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryByName(String name) {
        return categoryConverter.categoryToCategoryDto(categoryRepository.getCategoryByName(name));
    }

    @Override
    public CategoryDto getCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundExceptionById("Specified category not exist id: " + id);
        }
        Category category = categoryRepository.getCategoryById(id);
        return categoryConverter.categoryToCategoryDto(category);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryConverter::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> getCategoryByParentCategoryIdEquals(Integer id) {
        return categoryRepository.getCategoryByParentCategoryIdEquals(id)
                .stream()
                .map(categoryConverter::categoryToCategoryDto)
                .collect(Collectors.toList());
    }

    private void validateCategoryDto(CategoryDto categoryDto) throws ValidationException {
        if (isNull(categoryDto)) {
            throw new ValidationException("Category object is null.");
        }
        if (isNull(categoryDto.getName()) || categoryDto.getName().isEmpty()) {
            throw new ValidationException("Category name value can not be empty.");
        }
    }
}
