package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class User_ShoppingCartService {
    @Resource
    ShoppingCartRepository shoppingCartRepository;

    public ShoppingCart save(ShoppingCart shoppingCart){
        return shoppingCartRepository.save(shoppingCart);
    }

    public List<ShoppingCart> findAllByUserId(Long UserID){
        return shoppingCartRepository.findAllByUserId(UserID);
    }
}
