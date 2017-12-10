package com.project_management.shoppingweb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.repository.SellerRepository;

@Service
public class SellerService {
	@Resource
	SellerRepository sellerRepository;
	
	public Seller findById(Long sellerId) {
		return sellerRepository.findBySellerId(sellerId);
	}
}
