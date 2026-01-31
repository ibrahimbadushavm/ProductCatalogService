package com.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Setter
@Getter
@Entity
@Document(indexName = "product")
public class Product extends BasModel {

    private String title;
    private String description;
    private String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    @Field(type = FieldType.Object)
    private Category category;
    private Double price;
//    private int quantity;
}
