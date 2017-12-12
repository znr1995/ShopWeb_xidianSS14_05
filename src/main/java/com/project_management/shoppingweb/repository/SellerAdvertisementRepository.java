package com.project_management.shoppingweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_management.shoppingweb.domain.SellerAdvertisement;

@Repository
public interface SellerAdvertisementRepository extends JpaRepository<SellerAdvertisement,Long> {
    //publish
	SellerAdvertisement save(SellerAdvertisement sellerAdvertisement);
    //getAdInf
    List<SellerAdvertisement> findBySellerId( Long sellerId);
    List<SellerAdvertisement> findAllByStatus(Integer status);
    SellerAdvertisement findByAdvertisementId(Long advertisementId);

}