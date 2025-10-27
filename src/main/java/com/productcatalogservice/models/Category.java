package com.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category  extends  BasModel{

    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
