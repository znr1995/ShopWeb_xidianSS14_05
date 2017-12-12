package com.project_management.shoppingweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project_management.shoppingweb.domain.Price;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	Price save(Price price); 
	
	//@Query("select c from Price c where c.priceId=1")
	List<Price> findAll();
}
