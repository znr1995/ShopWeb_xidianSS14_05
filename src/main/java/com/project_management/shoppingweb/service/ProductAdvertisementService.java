package com.project_management.shoppingweb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.repository.ProductAdvertisementRepository;

@Service
public class ProductAdvertisementService {
	@Resource
	ProductAdvertisementRepository productAdvertisementRepository;
	public List<ProductAdvertisement> findAllByStatus(Integer status){
		return productAdvertisementRepository.findAllByStatus(status);
	}
	public ProductAdvertisement findByAdvertisementId(Long advertisementId) {
		return productAdvertisementRepository.findByAdvertisementId(advertisementId);
	}
}
