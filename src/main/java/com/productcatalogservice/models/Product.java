package com.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends  BasModel{

    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne
    private Category category;
    private Double price;
}
