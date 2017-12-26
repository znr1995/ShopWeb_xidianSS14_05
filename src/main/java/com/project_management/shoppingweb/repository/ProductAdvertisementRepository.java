package com.project_management.shoppingweb.repository;

import java.util.List;

import com.project_management.shoppingweb.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_management.shoppingweb.domain.ProductAdvertisement;

@Repository
public interface ProductAdvertisementRepository extends JpaRepository<ProductAdvertisement,Long>{
    List<ProductAdvertisement> findAllByType(Integer type);
	List<ProductAdvertisement> findAllByStatus(Integer status);
	ProductAdvertisement findByAdvertisementId(Long advertisementId);
	ProductAdvertisement save(ProductAdvertisement productAdvertisement);
	List<ProductAdvertisement> findAllByProductId(long productId);
	void delete(ProductAdvertisement productAdvertisement);

}
