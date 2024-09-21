package com.team.rambla.websitedbspringboot.service;

import com.team.rambla.websitedbspringboot.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();
    Category getCategoryById(Long id);

    List<Category> saveCategory(Category category);

    Category updateCategory(Category category, Long id);

    Category deleteCategory(Long id);
}
