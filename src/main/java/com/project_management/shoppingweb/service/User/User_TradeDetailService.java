package com.project_management.shoppingweb.service.User;


import com.project_management.shoppingweb.domain.TradeDetail;
import com.project_management.shoppingweb.repository.TradeDetailRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User_TradeDetailService {
    @Resource
    TradeDetailRepository tradeDetailRepository;

    public TradeDetail save(TradeDetail tradeDetail){
        return tradeDetailRepository.save(tradeDetail);
    }
    public List<TradeDetail> findByTradeId(Long TradeID){
        return tradeDetailRepository.findByTradeId(TradeID);
    }
    public TradeDetail findByTradeDetailId(Long TradeDetailID){
        return tradeDetailRepository.findByTradeDetailId(TradeDetailID);
    }
    public List<TradeDetail> findAllByProductId(int ProductID){
        return tradeDetailRepository.findByproductId(ProductID);
    }
}
