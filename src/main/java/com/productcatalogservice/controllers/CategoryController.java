package com.productcatalogservice.controllers;

import com.productcatalogservice.dtos.CategoryDto;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {


    private CategoryService categoryService;

    public CategoryController(@Qualifier("fakeStoreCategoryService") CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<Category> categories= categoryService.getAllCategories();
        for (Category category : categories) {
            categoryDtos.add(fromCategory(category));
        }
        return categoryDtos;
    }


    @PostMapping()
    public CategoryDto addCategory(@RequestBody String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        return null; // Placeholder for actual implementation
    }

    private CategoryDto fromCategory(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(category.getName());
        return categoryDto;
    }

}
