package com.team.rambla.websitedbspringboot.service.impl;

import com.team.rambla.websitedbspringboot.entity.Category;
import com.team.rambla.websitedbspringboot.repository.CategoryRepository;
import com.team.rambla.websitedbspringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new RuntimeException("No category found for id:" + id));

    }

    @Override
    public List<Category> saveCategory(Category category) {
        repository.save(category);
        return repository.findAll();
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category categoryToUpdate = repository.findById(id).orElseThrow(()-> new RuntimeException("There is no category with id:" + id));
        categoryToUpdate.setName(category.getName());
        return repository.save(categoryToUpdate);
    }

    @Override
    public Category deleteCategory(Long id) {
        Category categoryToDelete = repository.findById(id).orElseThrow(()-> new RuntimeException("There is no category with id:" + id));
        repository.delete(categoryToDelete);
        return categoryToDelete;
    }
}
