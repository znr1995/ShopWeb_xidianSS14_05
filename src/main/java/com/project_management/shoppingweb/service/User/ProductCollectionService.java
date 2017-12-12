package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.repository.ProductCollectionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductCollectionService {
    @Resource
    ProductCollectionRepository productCollectionRepository;

    public ProductCollection save(ProductCollection productCollection){
        return productCollectionRepository.save(productCollection);
    }
    public List<ProductCollection> findAllByUserId(Long UserID){
        return productCollectionRepository.findAllByUserId(UserID);
    }
}
