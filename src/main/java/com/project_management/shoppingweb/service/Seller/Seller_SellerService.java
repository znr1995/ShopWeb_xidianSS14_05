package com.project_management.shoppingweb.service.Seller;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.repository.ProductRepository;
import com.project_management.shoppingweb.repository.SellerAdvertisementRepository;
import com.project_management.shoppingweb.repository.SellerRepository;
import org.dom4j.io.STAXEventReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Seller_SellerService {

    @Resource
    private SellerRepository sellerRepository;

    @Resource
    private SellerAdvertisementRepository sellerAdvertisementRepository;

    @Resource
    private ProductRepository productRepository;

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
       deleteProduct(product.getProductId());
    }

    public void deleteProduct(long productId)
    {
        productRepository.deleteByProductId(productId);
    }
}
