package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.ProductAdvertisement;
import com.project_management.shoppingweb.repository.ProductAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_ProductAdvertisementService {
    @Autowired
    ProductAdvertisementRepository productAdvertisementRepository;

    public List<ProductAdvertisement> findAllByType(Integer type){
        return  productAdvertisementRepository.findAllByType(type);
    }
}
