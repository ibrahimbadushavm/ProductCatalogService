package com.productcatalogservice.services;

import com.productcatalogservice.clients.fakestoreclient.FakeStoreApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakeStoreCategoryService implements CategoryService {

    @Autowired
    private FakeStoreApiClient  fakeStoreApiClient;

    @Override
    public List<String> getAllCategories() {
        return fakeStoreApiClient.getCategories();
    }
}
