package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.repository.TradeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User_TradeService {
    @Resource
    TradeRepository tradeRepository;

    public Trade save(Trade trade){
        return tradeRepository.save(trade);
    }
    public List<Trade> findAllByUserId(Long UserID){
        return tradeRepository.findAllByUserId(UserID);
    }

    public List<Trade> findByTradeId(Long TradeID) {return tradeRepository.findByTradeId(TradeID);}
}
