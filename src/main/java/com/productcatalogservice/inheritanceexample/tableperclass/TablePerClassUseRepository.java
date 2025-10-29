package com.productcatalogservice.inheritanceexample.tableperclass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablePerClassUseRepository extends JpaRepository<User,Long> {
}
