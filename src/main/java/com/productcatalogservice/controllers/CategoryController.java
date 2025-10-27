package com.productcatalogservice.controllers;

import com.productcatalogservice.dtos.CategoryDto;
import com.productcatalogservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping()
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }


//    @PostMapping()
//    public String addCategory(@RequestBody String categoryName) {
//
//    }

}
