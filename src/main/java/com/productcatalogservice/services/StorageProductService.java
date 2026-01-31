package com.productcatalogservice.services;

import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import com.productcatalogservice.repositories.CategoryRepository;
import com.productcatalogservice.repositories.ProductRepository;
import com.productcatalogservice.repositories.products.ElasticSearchProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StorageProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ElasticSearchProductRepository elasticSearchProductRepository;

    public StorageProductService(ProductRepository productRepository, CategoryRepository categoryRepository,ElasticSearchProductRepository elasticSearchProductRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.elasticSearchProductRepository=elasticSearchProductRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) throws NotFoundException {
//        if(productStorage.containsKey(id)){
//            return Optional.of(productStorage.get(id));
//        }
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
//        productStorage.put(id, productOptional.get());
        return productOptional;
    }

    @Override
    public Product addProduct(Product product) {
        Optional<Category> categoryOptional = categoryRepository.findByName(product.getCategory().getName());
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
        }
        Product savedProduct = productRepository.save(product);
        elasticSearchProductRepository.save(savedProduct);
        return savedProduct;
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
            Product updatedProduct = productRepository.save(existingProduct);
            elasticSearchProductRepository.save(updatedProduct);
            return updatedProduct;
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

    @Override
    public Page<List<Product>> getProductsPagedHavvingTitleContaining(String query, Integer page, Integer size) {
        return productRepository.findByTitleContaining(query, PageRequest.of(page, size,Sort.by("price").ascending()));
    }

    @Override
    public Page<List<Product>> searchProducts(String query,Integer page, Integer size) {
        return elasticSearchProductRepository.searchProducts(query,PageRequest.of(page,size));
    }
}
