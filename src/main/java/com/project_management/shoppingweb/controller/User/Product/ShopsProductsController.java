package com.project_management.shoppingweb.controller.User.Product;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopsProductsController {

    @Autowired
    private User_ProductService userProductService;
    @Autowired
    private UserService userService;
    @Autowired
    private SellerService sellerService;
    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/{sellerid}")//商铺内商品页面
    public String ShopsProduct(Model model, HttpServletRequest str, @PathVariable("sellerid") Long sellerid){
        List<Product> products =
               userProductService.findAllBySellerID(sellerid);
        boolean pro_isnull = false;
        if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
        long UserID = Long.parseLong(str.getParameter("UserID"));
        model.addAttribute("UserID", UserID);
        model.addAttribute("products",products);
        Seller seller = sellerService.findBySellerId(sellerid);
        model.addAttribute("seller", seller);
        model.addAttribute("ShopID", sellerid);
        model.addAttribute("pro_isnull", pro_isnull);
        if(UserID == -1){
            model.addAttribute("UserName", "UserName");
        }
        else{
            User user = userService.findByUserId(UserID);
            model.addAttribute("UserName", user.getUsername());
        }
        return "/User/sellersproduct";
    }

}
