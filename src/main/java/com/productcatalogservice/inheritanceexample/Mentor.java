package com.productcatalogservice.inheritanceexample;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mentor extends User {
    private int numberOfMentees;
}
