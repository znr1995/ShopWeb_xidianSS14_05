package com.project_management.shoppingweb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.Advertisement;
import com.project_management.shoppingweb.repository.AdvertisementRepository;

@Service
public class AdvertisementService {
	@Resource
	AdvertisementRepository advertisementRepository;
	
	public Advertisement findById(Long advertisementId) {
		return advertisementRepository.findByAdvertisementId(advertisementId);
	}
}
