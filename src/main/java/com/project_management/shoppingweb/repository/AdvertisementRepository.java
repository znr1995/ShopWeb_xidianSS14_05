package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement,Long> {
    //publish
    Advertisement save(Advertisement advertisement);
    //getAdInf
    List<Advertisement> findBySellerId( int sellerId);
    List<Advertisement> findAll();

}