package com.ugur.service;

import com.ugur.dto.ProductDto;
import com.ugur.exception.BusinessException;
import com.ugur.exception.EntityNotFoundExceptionById;

import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProductService {
    ProductDto create(ProductDto productDto) throws ValidationException, PropertyException;

    void delete(Integer id) throws EntityNotFoundExceptionById;

    ProductDto update(ProductDto productDto) throws ValidationException, BusinessException;

    ProductDto getProduct(Integer id);

    List<ProductDto> getAllByCategoryId(Integer id);

}
