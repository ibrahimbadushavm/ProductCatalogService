package com.productcatalogservice.inheritanceexample.joinedtable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinedClassMentorRepository extends JpaRepository<Mentor,Long> {
}
