package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.service.User.ProductCollectionService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyFavoriteController {
    @Autowired
    private ProductCollectionService productCollectionService;
    @RequestMapping(value = "/MyFavorite",method = RequestMethod.GET)
    public String MyFavorite(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");

        if(UserID.equals("")){
            String ProductID = request.getParameter("ProductID");
            String ShopID = request.getParameter("ShopID");
            String UnitPrice = request.getParameter("UnitPrice");
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/productdetial";
        }

        List<ProductCollection> FavoritelistP = new ArrayList<ProductCollection>();
        FavoritelistP = productCollectionService.findAllByUserId(Long.parseLong(UserID));


        if(FavoritelistP.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/Favorite";
        }


        List<String> FavoriteProductID = new ArrayList<String>();
        for(int i = 0; i < FavoritelistP.size(); i++){
            FavoriteProductID.add(String.valueOf(FavoritelistP.get(i).getProductId()));
        }
            model.addAttribute("ProductList", FavoriteProductID);

        model.addAttribute("UserID", UserID);
        return "/User/Favorite";

    }
}
