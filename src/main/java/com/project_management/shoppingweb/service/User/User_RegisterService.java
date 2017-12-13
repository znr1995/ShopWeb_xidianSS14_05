package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.repository.SellerRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class User_RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    //TODO:register wight

    public boolean userVaildUsername(String username)
    {
        if(userRepository.findByUsername(username) == null)
            return  true;
        return false;
    }

    public boolean userVaildEmail(String email)
    {
        if(userRepository.findByEmail(email) == null)
            return  true;
        return false;
    }

    public long userRegister(User user)
    {
        return userRepository.save(user).getUserId();
    }

    public boolean sellerVaildUsername(String username)
    {
        if(sellerRepository.findByUsername(username) == null)
            return  true;
        return false;
    }

    public boolean sellerVaildEmail(String email)
    {
        if(sellerRepository.findByEmail(email) == null)
            return true;
        return false;
    }

    public long sellerRegister(Seller seller)
    {
        return sellerRepository.save(seller).getSellerId();
    }

}
