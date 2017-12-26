package com.project_management.shoppingweb.service.User;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.repository.SellerRepository;
import com.project_management.shoppingweb.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 错误处理,用户名/邮箱/密码错误可以提醒
 */
@Service
public class User_LoginService {

    @Resource
    SellerRepository sellerRepository;

    @Resource
    UserRepository userRepository;


    //  -1 --没有用户   -2--代表密码不对  正确,返回sellerId
    public long sellerLoginByEmail(String email,String password)
    {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller == null)
            return -1;
        if(seller.getPassword().equals(password))
            return seller.getSellerId();
        return -2;
    }

    public long sellerLoginByUsername(String username, String password)
    {
        Seller seller = sellerRepository.findByUsername(username);
        if(seller == null)
            return -1;
        if(seller.getPassword().equals(password))
            return seller.getSellerId();
        return -2;
    }

    //  -1 --没有用户   -2--代表密码不对  正确,返回userId
    public long userLoginByEmail(String email,String password)
    {
        User user = userRepository.findByEmail(email);
        if(user == null)
            return -1;
        if(user.getPassword().equals(password))
            return user.getUserId();
        return -2;
    }

    public long userLoginByUsername(String username,String password)
    {
        User user = userRepository.findByEmail(username);
        if(user == null)
            return -1;
        if(user.getPassword().equals(password))
            return user.getUserId();
        return -2;
    }


}
