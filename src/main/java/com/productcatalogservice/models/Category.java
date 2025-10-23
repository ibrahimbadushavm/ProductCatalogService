package com.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category  extends  BasModel{

    private String name;
    private List<Product> products;
}
