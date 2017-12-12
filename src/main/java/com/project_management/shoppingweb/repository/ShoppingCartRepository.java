package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long>{
    ShoppingCart save(ShoppingCart shoppingCart);
    List<ShoppingCart> findByUserId(Long usrId);
}
