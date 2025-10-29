package com.productcatalogservice.inheritanceexample.mappedsupperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Instructor extends User {
    private int numberOfHours;
    private int batchNo;
}
