package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ShoppingCartInHomeController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/ShoppingCartInHome", method = RequestMethod.GET)

    public String ShoppingCartInHome(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        User user = userService.findByUserId(Long.parseLong(UserID));

        if(UserID.equals("-1")){
//            String ProductID = request.getParameter("ProductID");
//            String ShopID = request.getParameter("ShopID");
//            String UnitPrice = request.getParameter("UnitPrice");
//            Product product = productService.findProductByProductID(Long.parseLong(ProductID));
//            String ProductName = product.getProductName();
//
//            model.addAttribute("ShopID", ShopID);
//            model.addAttribute("UserID", UserID);
//            model.addAttribute("ProductID", ProductID);
//            model.addAttribute("UnitPrice", UnitPrice);
//            model.addAttribute("ProductName", ProductName);
            return "/User/loginNew";
        }


        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = shoppingCartService.findAllByUserId(Long.parseLong(UserID));


        if(GlobalShoppingCart.size() == 0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("UserName",user.getUsername());
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
                shoppingCartToShow.UPrice = "/";
                shoppingCartToShow.TPrice = "/";
                shoppingCartToShowList.add(shoppingCartToShow);
                continue;
            }
            shoppingCartToShow.Name = product.getProductName();
            shoppingCartToShow.Photo = product.getProductPhoto();
            shoppingCartToShow.UPrice = String.valueOf(product.getProductPrice());
            shoppingCartToShow.TPrice = String.valueOf(product.getProductPrice() * shoppingCartToShow.shoppingCart.getProductAmount());
            shoppingCartToShowList.add(shoppingCartToShow);
        }

        model.addAttribute("UserID", UserID);
        model.addAttribute("GlobalShoppingCart", shoppingCartToShowList);
        model.addAttribute("UserName",user.getUsername());

        return "/User/ShoppingCartNew";
    }
}

