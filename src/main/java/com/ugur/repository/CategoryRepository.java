package com.ugur.repository;

import com.ugur.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /**
     *
     * @param name
     * @return
     */
    Category getCategoryByName(String name);

    /**
     *
     * @param id
     * @return
     */
    Category getCategoryById(Integer id);

    /**
     *
     * @param id
     * @return
     */
    List<Category> getCategoryByParentCategoryIdEquals(Integer id);
}
