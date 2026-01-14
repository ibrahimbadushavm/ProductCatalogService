package com.productcatalogservice.models;

import com.productcatalogservice.repositories.CategoryRepository;
import com.productcatalogservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository  categoryRepository;



//    @Test
    void TestLazyLoading(){
        Product product = new Product();
        product.setImageUrl("MacBeth.jpg");
        product.setDescription("Best book ever");
        Category category = new  Category();
        category.setName("Book");
        product.setCategory(category);

        productRepository.save(product);

//        categoryRepository.save(category);
    }
}