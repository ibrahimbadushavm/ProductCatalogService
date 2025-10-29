package com.productcatalogservice.inheritanceexample.joinedtable;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {
    @Autowired
    private JoinedClassMentorRepository joinedClassMentorRepository;
    @Autowired
    private JoinedClassUserRepository joinedClassUserRepository;

    @Test
    void TestJoinedTableInheritanceExample() {
        User john = new User();
        john.setFirstName("John");
        john.setLastName("Doe");

        joinedClassUserRepository.save(john);
    }
}