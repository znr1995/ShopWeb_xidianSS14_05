package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.SellerAdvertisement;
import com.project_management.shoppingweb.repository.SellerAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class User_SellerAdvertisementService {
    @Autowired
    SellerAdvertisementRepository sellerAdvertisementRepository;

    public List<SellerAdvertisement> findAllByStatus(Integer status){
        return sellerAdvertisementRepository.findAllByStatus(status);
    }
}
