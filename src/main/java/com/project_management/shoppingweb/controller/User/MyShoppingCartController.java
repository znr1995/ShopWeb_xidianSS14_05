package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import com.project_management.shoppingweb.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyShoppingCartController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/MyShoppingCart", method = RequestMethod.GET)
    public String MyShoppingCart(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");


        if(UserID.equals("-1")){
            String ProductID = request.getParameter("ProductID");
            String ShopID = request.getParameter("ShopID");
            String UnitPrice = request.getParameter("UnitPrice");
            Product product = productService.findProductByProductID(Long.parseLong(ProductID));
            String ProductName = product.getProductName();

            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }


        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = shoppingCartService.findAllByUserId(Long.parseLong(UserID));

        model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());


        if(GlobalShoppingCart.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/ShoppingCartNew";
        }

        List<ShoppingCartToShow> shoppingCartToShowList = new ArrayList<ShoppingCartToShow>();
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            ShoppingCartToShow shoppingCartToShow = new ShoppingCartToShow();
            shoppingCartToShow.shoppingCart = GlobalShoppingCart.get(i);
            Product product = productService.findProductByProductID(GlobalShoppingCart.get(i).getProductId());
            if(product == null){
                shoppingCartToShow.Name = "undercarriage";
                shoppingCartToShow.Photo = "233";
                shoppingCartToShowList.add(shoppingCartToShow);
                continue;
            }
            shoppingCartToShow.Name = product.getProductName();
            shoppingCartToShow.Photo = product.getProductPhoto();
            shoppingCartToShowList.add(shoppingCartToShow);
        }

        model.addAttribute("UserID", UserID);
        model.addAttribute("GlobalShoppingCart", shoppingCartToShowList);

        return "/User/ShoppingCartNew";


    }
}

class ShoppingCartToShow{
    public ShoppingCart shoppingCart;
    public String Name;
    public String Photo;
}
