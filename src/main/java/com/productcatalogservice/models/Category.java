package com.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.WriteOnlyProperty;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Document(indexName = "category")
public class Category  extends  BasModel {

    private String name;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade ={ CascadeType.REMOVE})
    @org.springframework.data.annotation.ReadOnlyProperty
//    @org.springframework.data.elasticsearch.annotations.Field(enabled = false)
    private List<Product> products;
}
