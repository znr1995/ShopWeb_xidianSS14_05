package com.project_management.shoppingweb.repository;


import com.project_management.shoppingweb.domain.Price;


import com.project_management.shoppingweb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> getAllBySellerId(long sellerId);
    void deleteByProductId(long productId);
    Product getByProductId(long productId);
    List<Product> findAllBySellerId(long sellerId);
    List<Product> findAllByProductName(String name);
    List<Product> findAllByType(String type);
    Product findProductByProductId(Long id);


    //createProduct
    Product save(Product product);
    //searchPro
    Product findByProductId(Long productId);
    List<Product> findByProductName(String productName);
    List<Product> findByType(Long type);
    List<Product> findBySellerId(Long sellerId);
    //deleteProduct
   // void delete(Product product);

}
