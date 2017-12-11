package com.project_management.shoppingweb.service;

import java.util.List;

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
	
	public List<Advertisement> findAllByStatus(Integer status) {
		return advertisementRepository.findAllByStatus(status);
	}
}
