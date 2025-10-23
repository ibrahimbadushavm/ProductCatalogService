package com.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends  BasModel{

    private String title;
    private String description;
    private String imageUrl;
    private Category category;
}
