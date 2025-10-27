package com.productcatalogservice.services;

import com.productcatalogservice.dtos.ProductDto;
import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> getAllProducts();

    public Optional<Product> getProductById(Long id) throws NotFoundException;

    public Product addProduct(Product product);

    public Product updateProduct(Long productId, Product product);

    public Product replaceProduct(Long productId, Product product);

    public Optional<Product> deleteProduct(Long productId) throws NotFoundException;
}
