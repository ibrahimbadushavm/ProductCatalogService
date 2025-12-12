package com.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Setter
@Getter
@Entity
public class Product extends  BasModel{

    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
    private Double price;
//    private int quantity;
}
