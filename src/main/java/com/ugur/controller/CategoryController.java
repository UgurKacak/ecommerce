package com.ugur.controller;

import com.ugur.dto.CategoryDto;
import com.ugur.exception.BusinessException;
import com.ugur.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/categories")
@AllArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/")
    public CategoryDto create(@RequestBody CategoryDto categoryDto) throws ValidationException, BusinessException {
        log.info("Category create operation invoked for: " + categoryDto);
        return categoryService.create(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        log.info("Delete category operation invoked for: " + id);
        categoryService.delete(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Category successfully deleted.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Map<String, String>> update(@RequestBody CategoryDto updateCategoryDto) throws ValidationException, BusinessException {
        log.info("Update category operation invoked for: " + updateCategoryDto);
        categoryService.update(updateCategoryDto);


        Map<String, String> map = new HashMap<>();
        map.put("message", "Category successfully updated.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/")
    public List<CategoryDto> getAllCategories() {
        log.info("Get all categories operation invoked.");
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public CategoryDto getCategory(@PathVariable Integer id) {
        log.info("Get categories operation invoked. id: " + id);
        return categoryService.getCategory(id);
    }

    @GetMapping("/getAllByParentCategoryId/{id}")
    public List<CategoryDto> getCategoryByParentCategoryId(@PathVariable Integer id) {
        log.info("Get category by parentCategoryId operation invoked.");
        return categoryService.getCategoryByParentCategoryIdEquals(id);
    }

}
