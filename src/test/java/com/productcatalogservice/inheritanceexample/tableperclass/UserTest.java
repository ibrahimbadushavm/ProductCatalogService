package com.productcatalogservice.inheritanceexample.tableperclass;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {
    @Autowired
    private TablePerClassMentorRepository mentorRepository;
    @Autowired
    private TablePerClassUseRepository tablePerClassUseRepository;

    @Test
    void testTablePerClassInheritance_And_ResultOk() {
        Mentor mentor = new Mentor();
        mentor.setFirstName("Mentor");
        mentor.setLastName("Roman");
        mentor.setNumberOfMentees(3);
        mentorRepository.save(mentor);
    }

}