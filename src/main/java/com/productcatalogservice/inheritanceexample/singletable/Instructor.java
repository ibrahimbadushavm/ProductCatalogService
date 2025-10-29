package com.productcatalogservice.inheritanceexample.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructor")
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private int numberOfHours;
    private int batchNo;
}
