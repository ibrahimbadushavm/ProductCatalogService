package com.productcatalogservice.repositories.products;

import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticSearchCategoryRepository extends ElasticsearchRepository<Category, Long> {
}
