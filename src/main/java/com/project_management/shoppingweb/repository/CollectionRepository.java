package com.project_management.shoppingweb.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_management.shoppingweb.domain.ProductCollection;
import java.util.List;

@Repository
public interface CollectionRepository extends JpaRepository <ProductCollection,Long> {

}
