package com.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter

public abstract class BasModel {
    private Long Id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}
