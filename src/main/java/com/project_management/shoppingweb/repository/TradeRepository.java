package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long>{
    Trade save(Trade trade);
    List<Trade> findAllByUserId(Long UserID);
}
