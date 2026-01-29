package com.productcatalogservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
public class Category  extends  BasModel implements Serializable {

    private String name;
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade ={ CascadeType.REMOVE})
    private List<Product> products;
}
