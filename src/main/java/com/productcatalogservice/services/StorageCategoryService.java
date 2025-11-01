package com.productcatalogservice.services;

import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import com.productcatalogservice.repositories.CategoryRepository;
import com.productcatalogservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageCategoryService implements CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public StorageCategoryService(CategoryRepository categoryRepository,ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsWithCategory(Long categoryId) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category Not Found");
        }
        Category category = categoryOptional.get();
        return productRepository.findAllByCategory(category);
    }
}
