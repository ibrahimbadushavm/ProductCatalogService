package com.productcatalogservice.controllers;

import com.productcatalogservice.dtos.ProductDto;
import com.productcatalogservice.models.Product;
import com.productcatalogservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProducts(){
        return productService.getAllProducts();
    }

    @PostMapping()
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.addProduct(productDto);
    }

    @GetMapping("/{productId}")
    public ProductDto getProductById(@PathVariable("productId") Long productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/{productId}")
    public ProductDto updateProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto) {
        return productService.updateProduct(productId,productDto);
    }

    @DeleteMapping("/{productId}")
    public ProductDto deleteProduct(@PathVariable("productId") Long productId) {
        return productService.deleteProduct(productId);
    }
}
