package com.productcatalogservice.inheritanceexample.mappedsupperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User {
    private int numberOfMentees;
}
