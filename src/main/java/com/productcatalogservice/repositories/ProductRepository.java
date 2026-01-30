package com.productcatalogservice.repositories;

import com.productcatalogservice.models.Category;
import com.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    Page<List<Product>> findByTitleContaining(String title, Pageable pageable);
}
