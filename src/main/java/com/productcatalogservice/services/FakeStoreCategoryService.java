package com.productcatalogservice.services;

import com.productcatalogservice.clients.fakestoreclient.FakeStoreApiClient;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {

    @Autowired
    private FakeStoreApiClient  fakeStoreApiClient;

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public List<Product> getAllProductsWithCategory(Long categoryId) {
        return List.of();
    }
}
