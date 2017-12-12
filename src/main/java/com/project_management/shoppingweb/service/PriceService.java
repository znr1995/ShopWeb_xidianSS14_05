package com.project_management.shoppingweb.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project_management.shoppingweb.domain.Price;
import com.project_management.shoppingweb.repository.PriceRepository;

@Service
public class PriceService {

	@Resource
	PriceRepository priceRepository ;
	public void updatePrice(Price price) {
		price.setCreateTime(new Date());
		priceRepository.save(price);
	}
}
