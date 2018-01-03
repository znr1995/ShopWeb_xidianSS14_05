package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.ShopCollection;
import com.project_management.shoppingweb.repository.ShopCollectionRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User_ShopCollectionService {
    @Resource
    ShopCollectionRepository shopCollectionRepository;

    public ShopCollection save(ShopCollection shopCollection){
        return shopCollectionRepository.save(shopCollection);
    }
    public List<ShopCollection> findAllByUserId(Long UserID){
        return shopCollectionRepository.findAllByUserId(UserID);
    }
    public void delete(ShopCollection shopCollection){
        shopCollectionRepository.delete(shopCollection);
    }
    public List<ShopCollection> findAllById(Long ID){
        return shopCollectionRepository.findAllById(ID);
    }
}
