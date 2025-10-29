package com.productcatalogservice.inheritanceexample.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tbc_instructor")
public class Instructor extends User {
    private int numberOfHours;
    private int batchNo;
}
