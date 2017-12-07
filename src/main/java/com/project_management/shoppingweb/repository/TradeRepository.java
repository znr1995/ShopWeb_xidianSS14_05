package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeRepository extends JpaRepository<Trade,Long>{
    Trade save(Trade trade);
    List<Trade> findBySellerIdAndTradeStatus(int sellerId, int tradeStatus);
    List<Trade> findByUserIdAndTradeStatus(int userId,int tradeStatus);
    List<Trade> findByTradeId(int TradeId);
}
