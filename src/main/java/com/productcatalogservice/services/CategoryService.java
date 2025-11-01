package com.productcatalogservice.services;

import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();

    public List<Product> getAllProductsWithCategory( Long categoryId) throws NotFoundException;
}
