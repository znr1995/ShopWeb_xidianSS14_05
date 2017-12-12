package com.project_management.shoppingweb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.repository.SellerAdvertisementRepository;



@Service
public class SellerAdvertisementService {
	@Resource
	SellerAdvertisementRepository sellerAdvertisementRepository;
	
	public SellerAdvertisement findById(Long advertisementId) {
		return sellerAdvertisementRepository.findByAdvertisementId(advertisementId);
	}
	
	public List<SellerAdvertisement> findAllByStatus(Integer status) {
		return sellerAdvertisementRepository.findAllByStatus(status);
	}
}
