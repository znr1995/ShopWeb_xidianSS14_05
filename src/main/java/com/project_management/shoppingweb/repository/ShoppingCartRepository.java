package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long>{
    ShoppingCart save(ShoppingCart shoppingCart);
    List<ShoppingCart> findByUserId(int usrId);
}
