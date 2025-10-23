package com.productcatalogservice.services;

import com.productcatalogservice.dtos.ProductDto;
import com.productcatalogservice.models.Product;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProducts();

    public ProductDto getProductById(Long id);

    public ProductDto addProduct(ProductDto name);

    public ProductDto updateProduct(Long productId, ProductDto productDto);

    public ProductDto deleteProduct(Long productId);
}
