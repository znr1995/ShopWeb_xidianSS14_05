package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_ProductService {
    @Autowired
    ProductRepository productRepository;
    /*public List<Product> findAllByNameMatch(String name){
        return productRepository.findAllByProductNameMatches(name);
    }*/
    public List<Product> findAllByNameMatch(String name){
        return productRepository.findAllByProductName(name);
    }


    public List<Product> finAllByType(String type){
        return  productRepository.findAllByType(type);
    }

    public List<Product> findAllBySellerID(Long sellerid){
        return productRepository.findAllBySellerId(sellerid);
    }

    public Product findProductByProductID(Long id){
        return productRepository.findProductByProductId(id);
    }
}
