package com.productcatalogservice.inheritanceexample;

import com.productcatalogservice.inheritanceexample.singletable.Mentor;
import com.productcatalogservice.inheritanceexample.singletable.SingleTableUserRepository;
import com.productcatalogservice.inheritanceexample.singletable.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Autowired
    private SingleTableUserRepository singleTableUserRepository;

    @Autowired
    private SingleTableUserRepository singleTableMentorRepository;
    @Test
    void TestSingleTableInheritance_And_ResultOk() {

        Mentor mentor = new Mentor();
        mentor.setFirstName("Mentor");
        mentor.setLastName("User");
        mentor.setNumberOfMentees(2);

        singleTableUserRepository.save(mentor);

    }
}