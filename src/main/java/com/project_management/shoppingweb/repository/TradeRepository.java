package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeRepository extends JpaRepository<Trade,Long>{
    Trade save(Trade trade);
    List<Trade> findAllBySellerIdAndTradeStatus(Long sellerId, int tradeStatus);
    List<Trade> findAllByUserId(Long UserID);

    List<Trade> findBySellerIdAndTradeStatus(Long sellerId, int tradeStatus);
    List<Trade> findByUserIdAndTradeStatus(Long userId,int tradeStatus);
    List<Trade> findByTradeId(Long TradeId);
    List<Trade> findAllBySellerId(long sellerId);
    Trade findByTradeId(long tradeId);
    List<Trade> findAllByTradeStatus(int tradeStatus );

}
