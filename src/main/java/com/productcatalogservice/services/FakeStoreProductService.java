package com.productcatalogservice.services;

import com.productcatalogservice.clients.FakeStoreApiClient;
import com.productcatalogservice.dtos.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {
    private FakeStoreApiClient fakeStoreApiClient= new FakeStoreApiClient();
    @Override
    public List<ProductDto> getAllProducts() {
        return fakeStoreApiClient.getAllProducts();
    }

    @Override
    public ProductDto getProductById(Long id) {
        return fakeStoreApiClient.getProductById(id);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        return fakeStoreApiClient.addProduct(productDto);
    }

    @Override
    public ProductDto updateProduct(Long productId,ProductDto productDto) {
        return fakeStoreApiClient.updateProduct(productId,productDto);
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        return fakeStoreApiClient.deleteProduct(productId);
    }
}
