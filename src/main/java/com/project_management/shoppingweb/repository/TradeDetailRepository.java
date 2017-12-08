package com.project_management.shoppingweb.repository;

import com.project_management.shoppingweb.domain.TradeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeDetailRepository extends JpaRepository<TradeDetail,Long>{
    TradeDetail save(TradeDetail tradeDetail);
    List<TradeDetail> findByTradeId(int TradeId);
    TradeDetail findByTradeDetailId(int tradeDetailId);
}
