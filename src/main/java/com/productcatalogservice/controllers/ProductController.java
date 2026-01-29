package com.productcatalogservice.controllers;

import com.productcatalogservice.dtos.ProductDto;
import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import com.productcatalogservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService) {
        this.productService = productService;
    }


    @GetMapping()
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(fromProduct(product));
        }
        return productDtos;
    }

    @PostMapping()
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        Product product = fromProductDto(productDto);
        Product productSaved = productService.addProduct(product);
        return fromProduct(productSaved);
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable("productId") Long productId) throws NotFoundException {
            return fromProduct(productService.getProductById(productId).get());
    }

    @PatchMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {
        return fromProduct(productService.updateProduct(productId, fromProductDto(productDto)));
    }

    @PutMapping("/{productId}")
    public ProductDto replaceProduct(@RequestBody ProductDto productDto,@PathVariable Long productId) {
        return fromProduct(productService.replaceProduct(productId, fromProductDto(productDto)));
    }

    @DeleteMapping("/{productId}")
    public ProductDto deleteProduct(@PathVariable("productId") Long productId) throws NotFoundException {
        return fromProduct(productService.deleteProduct(productId).get());
    }

    private ProductDto fromProduct(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setCategory(product.getCategory().getName());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());
        productDto.setImage(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    private Product fromProductDto(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) {
            product.setId(productDto.getId());
        }
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
