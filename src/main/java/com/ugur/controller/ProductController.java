package com.ugur.controller;

import com.ugur.exception.BusinessException;
import com.ugur.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ugur.dto.ProductDto;

import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public ProductDto create(@RequestBody ProductDto productDto) throws ValidationException, PropertyException {
        log.info("Product create operation invoked for: " + productDto);
        return productService.create(productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Integer id) {
        log.info("Delete product operation invoked for: " + id);
        productService.delete(id);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Product successfully deleted.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Map<String, String>> update(@RequestBody ProductDto updateProductDto) throws ValidationException, BusinessException {
        log.info("Update product operation invoked for: " + updateProductDto);
        productService.update(updateProductDto);

        Map<String, String> map = new HashMap<>();
        map.put("message", "Product successfully updated.");

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable Integer id) {
        log.info("Get product operation invoked. id: " + id);
        return productService.getProduct(id);
    }

    @GetMapping("/getAllByCategoryId/{id}")
    public List<ProductDto> getAllbyCategoryId(@PathVariable Integer id) {
        log.info("Get all products by category id operation invoked.");
        return productService.getAllByCategoryId(id);
    }

}
