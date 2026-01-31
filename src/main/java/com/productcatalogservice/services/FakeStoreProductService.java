package com.productcatalogservice.services;

import com.productcatalogservice.clients.fakestoreclient.FakeStoreApiClient;
import com.productcatalogservice.clients.fakestoreclient.FakeStoreProductDto;
import com.productcatalogservice.dtos.ProductDto;
import com.productcatalogservice.exceptions.NotFoundException;
import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FakeStoreProductService implements ProductService {

    private FakeStoreApiClient fakeStoreApiClient;
    @Autowired
    private RedisTemplate<Long, Object> productsRedisTemplate;

    public FakeStoreProductService(FakeStoreApiClient fakeStoreApiClient) {
        this.fakeStoreApiClient = fakeStoreApiClient;
    }

    @Override
    public List<Product> getAllProducts() {

        List<FakeStoreProductDto> productDtos = fakeStoreApiClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto productDto : productDtos) {
            products.add(fromFakeStoreProductDto(productDto));
        }

        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) throws NotFoundException {
        if(productsRedisTemplate.opsForHash().hasKey(id, "PRODUCT")) {

            return Optional.of((Product) productsRedisTemplate.opsForHash().get(id, "PRODUCT"));
        }
        FakeStoreProductDto productDto = fakeStoreApiClient.getProductById(id);
        if(productDto == null) {
            throw new NotFoundException("Product not found with productId "+id);
        }
        productsRedisTemplate.opsForHash().put(id, "PRODUCT",fromFakeStoreProductDto(productDto));
        return Optional.of(fromFakeStoreProductDto(productDto)) ;
    }

    @Override
    public Product addProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto= fakeStoreApiClient.addProduct(fromProduct(product));
        return fromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto= fakeStoreApiClient.updateProduct(productId,fromProduct(product));
        return fromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        FakeStoreProductDto fakeStoreProductDto= fakeStoreApiClient.replaceProduct(productId,fromProduct(product));
        return fromFakeStoreProductDto(fakeStoreProductDto);
    }

    @Override
    public Optional<Product> deleteProduct(Long productId) throws NotFoundException {
        FakeStoreProductDto fakeStoreProductDto= fakeStoreApiClient.deleteProduct(productId);
        if(fakeStoreProductDto == null) {
            throw new NotFoundException("Product not found with productId "+productId);
        }
        return Optional.of(fromFakeStoreProductDto(fakeStoreProductDto));
    }

    @Override
    public Page<List<Product>> getProductsPagedHavvingTitleContaining(String title, Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<List<Product>> searchProducts(String query,Integer page, Integer size) {
        return null;
    }

    private FakeStoreProductDto fromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setTitle(product.getTitle());
        return fakeStoreProductDto;
    }

    private Product fromFakeStoreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProductDto.getCategory());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImageUrl(fakeStoreProductDto.getImage());
        return product;
    }
}
