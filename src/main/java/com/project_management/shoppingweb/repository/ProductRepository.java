package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>{
    //createProduct
    Product save(Product product);
    //searchPro
    Product findByProductId(int productId);
    List<Product> findByProductName(String productName);
    List<Product> findByType(int type);
    List<Product> findBySellerId(int sellerId);
    //deleteProduct
    void delete(Product product);
}
