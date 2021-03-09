package com.ugur.service.impl;

import com.ugur.converter.ProductConverter;
import com.ugur.dto.ProductDto;
import com.ugur.entity.Product;
import com.ugur.exception.BusinessException;
import com.ugur.exception.EntityNotFoundExceptionById;
import com.ugur.repository.ProductRepository;
import com.ugur.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.xml.bind.PropertyException;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;


@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    @Override
    public ProductDto create(ProductDto productDto) throws ValidationException, PropertyException {
        validateProductDto(productDto);
        Product product = productRepository.getProductByName(productDto.getName());

        if (product != null) {
            throw new PropertyException("Product: " + productDto.getName() + " is already exist.");
        }

        Product createdProduct = productRepository.save(productConverter.productDtoToProduct(productDto));
        log.info("Product create operation successful: " + createdProduct.getName());
        return productConverter.productToProductDto(createdProduct);
    }

    @Override
    public void delete(Integer id) throws EntityNotFoundExceptionById {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundExceptionById("Invalid id for delete operation | id:" + id);
        }
        productRepository.deleteById(id);
    }

    @Override
    public ProductDto update(ProductDto productDto) throws BusinessException, ValidationException {
        validateProductDto(productDto);

        Product product = productRepository.getProductById(productDto.getId());

        if (product == null) {
            throw new BusinessException("Product id: " + productDto.getId() + " is not exist.");
        }

        Product updatedProduct = productRepository.save(productConverter.productDtoToProduct(productDto));
        log.info("Product update operation successful: " + updatedProduct.getName());
        return productConverter.productToProductDto(updatedProduct);
    }

    @Override
    public ProductDto getProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new EntityNotFoundExceptionById("Specified product not exist id: " + id);
        }
        Product product = productRepository.getProductById(id);
        return productConverter.productToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllByCategoryId(Integer id) {
        return productRepository.getAllByCategoryId(id)
                .stream()
                .map(productConverter::productToProductDto)
                .collect(Collectors.toList());
    }

    private void validateProductDto(ProductDto productDto) throws ValidationException {
        if (isNull(productDto)) {
            throw new ValidationException("Product object is null.");
        }
        if (isNull(productDto.getName()) || productDto.getName().isEmpty() || isNull(productDto.getImageUrl()) || productDto.getImageUrl().isEmpty() || isNull(productDto.getPrice()) || isNull(productDto.getQuantity())) {
            throw new ValidationException("All Product attributes should be provided.");
        }
    }
}
