package com.productcatalogservice.services;

import com.productcatalogservice.clients.FakeStoreApiClient;
import com.productcatalogservice.models.Category;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {

    private FakeStoreApiClient  fakeStoreApiClient= new FakeStoreApiClient();
    @Override
    public List<String> getAllCategories() {
        return fakeStoreApiClient.getCategories();
    }
}
