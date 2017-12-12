package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.SellerAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerAdvertisementRepository extends JpaRepository<SellerAdvertisement,Long> {

}