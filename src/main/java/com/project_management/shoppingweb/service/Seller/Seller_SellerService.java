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
    private ProductAdvertisementRepository productAdvertisementRepository;

    @Resource
    private ProductRepository productRepository;

    @Resource
    private TradeRepository tradeRepository;

    @Resource
    private TradeDetailRepository tradeDetailRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private PriceRepository priceRepository;



    public Seller getSellerById(long sellerId)
    {
        return sellerRepository.findBySellerId(sellerId);
    }

    public User getUser(long userID)
    {
        return userRepository.findByUserId(userID);
    }


    public boolean writeInInformation(Seller seller)
    {
        Seller seller1 = sellerRepository.save(seller);
        if(seller1 != null)
            return true;
        return false;
    }





    //产品相关
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




    //交易相关
    public List<Trade> getTradeList(long sellerID, int statue)
    {

        if(statue == 3)
        {
            LinkedList<Trade> retTrades = new LinkedList<Trade>();
            retTrades.addAll(getTradeList(sellerID,1));
            retTrades.addAll(getTradeList(sellerID,0));
            return retTrades;
        }
        return tradeRepository.findBySellerIdAndTradeStatus(sellerID,statue);
    }

    public Trade getTrade(long trade)
    {
        return tradeRepository.findByTradeId(trade);
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




    //广告相关的接口
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


    public void deleteSellerAdvertisement(long advertisementId)
    {
        sellerAdvertisementRepository.delete(sellerAdvertisementRepository.getByAdvertisementId(advertisementId));
    }

    public ProductAdvertisement getProductAdvertisementByProductAdvertisementId(long productAdvertismentId)
    {
        return productAdvertisementRepository.findByAdvertisementId(productAdvertismentId);
    }

    public List<ProductAdvertisement> getProductAdvertisementBySellerID(long sellerID)
    {
     List<Product> products = productRepository.findAllBySellerId(sellerID);
     LinkedList<ProductAdvertisement> retlist = new LinkedList<ProductAdvertisement>();
     for(Product product : products)
     {
         retlist.addAll(productAdvertisementRepository.findAllByProductId(product.getProductId()));
     }
     return retlist;
    }

    public void writeInProductAdvertisement(ProductAdvertisement productAdvertisement)
    {
        productAdvertisementRepository.save(productAdvertisement);
    }

    public List<SellerAdvertisement> getSellerAdvertisementBySellerId(long sellerID)
    {
        return sellerAdvertisementRepository.getAllBySellerId(sellerID);
    }

    public void deleteProductAdvertisement(long advertisement)
    {
        productAdvertisementRepository.delete(productAdvertisementRepository.findByAdvertisementId(advertisement));
    }

    public SellerAdvertisement getSellerAdvertisemetBySellerAdvertisementId(long advertisementId)
    {
        return sellerAdvertisementRepository.getByAdvertisementId(advertisementId);
    }

    public void writeInSellerAdvertisement(SellerAdvertisement sellerAdvertisement)
    {
        sellerAdvertisementRepository.save(sellerAdvertisement);
    }

    public double getProductListAdvertisementPrice()
    {
        return priceRepository.findAll().get(0).getProductHighAdvertisementPrice();
    }

    public double getProductRollAdvertisementPrice()
    {
        return priceRepository.findAll().get(0).getProductLowAdvertisementPrice();
    }

    public double getSellerListAdvertisementPrice()
    {
        return priceRepository.findAll().get(0).getSellerListAdvertisementPrice();
    }
}
