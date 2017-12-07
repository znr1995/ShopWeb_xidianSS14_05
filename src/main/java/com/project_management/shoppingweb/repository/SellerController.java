package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerController extends JpaRepository<Seller,Long>{
    //注册信息_商家
    //changeinf
    Seller save(Seller seller);
    //loginByEmail
    Seller findByEmailAndPassword(String email,String password);
    Seller findByUsernameAndPassword(String username,String password);
    //getSellerInf
    Seller findBySellerId(String sellerId);
}
