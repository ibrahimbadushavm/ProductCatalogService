package com.productcatalogservice.services;

import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import com.productcatalogservice.repositories.CategoryRepository;
import com.productcatalogservice.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public StorageProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        return productOptional;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Optional<Product> productOptional = productRepository.findById(productId);
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());

        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            if (product.getTitle() != null) {
                existingProduct.setTitle(product.getTitle());
            }
            if (product.getDescription() != null) {
                existingProduct.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                existingProduct.setPrice(product.getPrice());
            }
            if (product.getImageUrl() != null) {
                existingProduct.setImageUrl(product.getImageUrl());
            }
            if (categoryOptional.isPresent()) {
                product.setCategory(categoryOptional.get());
            } else {
                existingProduct.setCategory(product.getCategory());
            }
            return productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        Product existingProduct = productRepository.findById(productId).get();
        existingProduct.setTitle(product.getTitle());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImageUrl(product.getImageUrl());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) throws NotFoundException {
        Optional<Product> existingProduct = productRepository.findById(productId);
        if (existingProduct.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        productRepository.delete(existingProduct.get());
        return existingProduct;
    }
}
