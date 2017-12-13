package com.project_management.shoppingweb.service.Seller;

import com.project_management.shoppingweb.domain.*;
import com.project_management.shoppingweb.repository.*;
import org.dom4j.io.STAXEventReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class Seller_SellerService {

    @Resource
    private SellerRepository sellerRepository;

    @Resource
    private SellerAdvertisementRepository sellerAdvertisementRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private TradeRepository tradeRepository;

    @Resource
    private TradeDetailRepository tradeDetailRepository;

    public Seller getSellerById(long sellerId)
    {
        return sellerRepository.findBySellerId(sellerId);
    }



    public boolean writeInInformation(Seller seller)
    {
        Seller seller1 = sellerRepository.save(seller);
        if(seller1 != null)
            return true;
        return false;
    }

    public List<SellerAdvertisement> getSellerAdvertisements(long sellerId)
    {
        return sellerAdvertisementRepository.getAllBySellerId(sellerId);
    }
    
    public  boolean writeInSellerAdvertisements(List<SellerAdvertisement> advertisements)
    {
        for(SellerAdvertisement sellerAdvertisement : advertisements)
            sellerAdvertisementRepository.save(sellerAdvertisement);
        return true;
    }

    public SellerAdvertisement getSellerAdvertisement(long advertisementId)
    {
        return sellerAdvertisementRepository.getByAdvertisementId(advertisementId);
    }
    
    public void deleteSellerAdvertisement(SellerAdvertisement advertisement)
    {
        deleteSellerAdvertisement(advertisement.getAdvertisementId());
    }

    public void deleteSellerAdvertisement(long advertisementId)
    {
        sellerAdvertisementRepository.deleteByAdvertisementId(advertisementId);
    }





    public List<Product> getSellerProducts(long sellerId)
    {
        return productRepository.getAllBySellerId(sellerId);
    }

    public boolean writeInProduct(Product product)
    {
        productRepository.save(product);
        return true;
    }

    public boolean writeInProducts(List<Product> products)
    {
        for(Product product : products)
            productRepository.save(product);
        return  true;
    }

    public Product getProduct(long productId)
    {
        return productRepository.getByProductId(productId);
    }

    public void deleteProduct(Product product)
    {
       productRepository.delete(product);
    }

    public void deleteProduct(long productId)
    {
        productRepository.delete(productRepository.getByProductId(productId));
    }

    public List<Trade> getTradeList(long sellerID, int statue)
    {
        return tradeRepository.findBySellerIdAndTradeStatus(sellerID,statue);
    }

    public List<Trade> getTradeListByTime(long sellerID, int statue, Date startDate, Date endDate)
    {
        List<Trade> reTrade = new LinkedList<Trade>();
        for(Trade trade : getTradeList(sellerID, statue))
        {
            if(endDate.after(trade.getTradeFinishTime()) && startDate.before(trade.getTradeFinishTime()))
            {
                reTrade.add(trade);
            }
        }
        return reTrade;
    }

    public double getTradeSum(List<Trade> trades)
    {
        double sum = 0.0;
        for(Trade trade : trades)
        {
            sum += trade.getTradeTotalMoney();
        }
        return sum;
    }

    public List<TradeDetail> getTradeList(long tradeID)
    {
        return tradeDetailRepository.findByTradeId(tradeID);
    }
}
