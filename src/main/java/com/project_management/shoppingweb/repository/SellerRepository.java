package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Long>{

}
