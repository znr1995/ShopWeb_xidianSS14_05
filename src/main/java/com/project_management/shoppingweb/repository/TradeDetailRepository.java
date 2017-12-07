package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.TradeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TradeDetailRepository extends JpaRepository<TradeDetail,Long>{
    TradeDetail save(TradeDetail tradeDetail);
    List<TradeDetail> findByTradeId(int TradeId);
    TradeDetail findByTradeDetailId(int tradeDetailId);
}
