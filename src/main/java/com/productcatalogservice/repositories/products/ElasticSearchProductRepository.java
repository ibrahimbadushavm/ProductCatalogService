package com.productcatalogservice.repositories.products;

import com.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface ElasticSearchProductRepository extends ElasticsearchRepository<Product, String> {

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"title\", \"description\"]}}")
    Page<List<Product>> searchProducts(String query,Pageable pageable);
}
