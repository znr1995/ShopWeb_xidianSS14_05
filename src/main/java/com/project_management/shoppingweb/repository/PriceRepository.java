package com.project_management.shoppingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project_management.shoppingweb.domain.Price;


@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	Price save(Price price); 
}
