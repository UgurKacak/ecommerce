package com.ugur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ugur.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     *
     * @param name
     * @return
     */
    Product getProductByName(String name);

    /**
     *
     * @param id
     * @return
     */
    Product getProductById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    List<Product> getAllByCategoryId(Integer id);
}
