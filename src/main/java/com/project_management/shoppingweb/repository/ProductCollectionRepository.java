package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.ProductCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCollectionRepository extends JpaRepository<ProductCollection,Long> {
    ProductCollection save(ProductCollection productCollection);
    List<ProductCollection> findAllByUserId(Long UserID);
}
