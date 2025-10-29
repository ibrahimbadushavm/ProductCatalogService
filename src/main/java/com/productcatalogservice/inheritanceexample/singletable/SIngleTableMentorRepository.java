package com.productcatalogservice.inheritanceexample.singletable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SIngleTableMentorRepository extends JpaRepository<Mentor, Long> {
}
