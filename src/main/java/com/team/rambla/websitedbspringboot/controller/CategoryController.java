package com.team.rambla.websitedbspringboot.controller;

import com.team.rambla.websitedbspringboot.entity.Category;
import com.team.rambla.websitedbspringboot.payload.request.CategoryPayload;
import com.team.rambla.websitedbspringboot.pojo.Header;
import com.team.rambla.websitedbspringboot.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categories")
    private ResponseEntity<CategoryPayload> getAllCategories() {
        List<Header> headers = new ArrayList<>();
        headers.add(Header.builder()
                .headerMapping("id")
                .headerName("ID")
                        .allowModify(false)
                .build());
        headers.add(Header.builder()
                        .headerName("Name")
                        .headerMapping("name")
                        .allowModify(true)
                .build());
        return ResponseEntity.ok(CategoryPayload.builder().headers(headers).categories(categoryService.getCategories()).build());
    }

    @GetMapping("/categories/{id}")
    private ResponseEntity<?> getCategoryById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(categoryService.getCategoryById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found for id:" + id);
        }
    }

    @PostMapping("/categories")
    private ResponseEntity<List<Category>> saveCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @DeleteMapping("/categories/{id}")
    private ResponseEntity<Category> deleteCategory(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }
}
