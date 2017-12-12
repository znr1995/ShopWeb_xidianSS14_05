package com.project_management.shoppingweb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.repository.SellerRepository;

import java.util.List;

@Service
public class SellerService {
	@Resource
	SellerRepository sellerRepository;
	
	public Seller findById(Long sellerId) {
		return sellerRepository.findBySellerId(sellerId);
	}
	public List<Seller> findAllByApplyState(Integer applyState){
		return sellerRepository.findAllByApplyState(applyState);
	}
	public Seller findBySellerId(Long sellerId){
		return sellerRepository.findBySellerId(sellerId);
	}
	public Seller save(Seller seller){
		return sellerRepository.save(seller);
	}
	public void delete(Seller seller){
		sellerRepository.delete(seller);
	}

}
