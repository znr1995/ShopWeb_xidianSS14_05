package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.SellerAdvertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerAdvertisementRepository extends JpaRepository<SellerAdvertisement,Long> {

    List<SellerAdvertisement> getAllBySellerId(long sellerId);
    SellerAdvertisement save(SellerAdvertisement sellerAdvertisement);
    void deleteByAdvertisementId(long advertisementId);
    SellerAdvertisement getByAdvertisementId(long advertisementId);
    public List<SellerAdvertisement> findAllByStatus(Integer status);
}