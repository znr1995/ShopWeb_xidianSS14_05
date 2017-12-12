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
public class AddIntoFavoritePController {
    @Autowired
    private ProductCollectionService productCollectionService;
    @RequestMapping(value = "/AddIntoFavoriteP",method = RequestMethod.GET)
    public String AddIntoFavoriteP(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");

        if(UserID.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("ProductID", ProductID);
            System.out.println("no");
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/Product";
        }

        List<ProductCollection> GlobalFavorites = new ArrayList<ProductCollection>();
        GlobalFavorites = productCollectionService.findAllByUserId(Long.parseLong(UserID));
        for(int i = 0; i < GlobalFavorites.size(); i++){
            if(GlobalFavorites.get(i).getUserId()== Long.parseLong(UserID)){
                model.addAttribute("UserID", UserID);
                model.addAttribute("ShopID", ShopID);
                model.addAttribute("ProductID", ProductID);
                System.out.println("no");
                model.addAttribute("UnitPrice", UnitPrice);
                return "/User/Product";
            }
        }

        ProductCollection favorite = new ProductCollection();
        favorite.setUserId(Long.parseLong(UserID));
        //favorite.setCollectionId((long)1);
        favorite.setProductId(Long.parseLong(ProductID));

        productCollectionService.save(favorite);

        System.out.println("ok");
        model.addAttribute("UserID", UserID);
        model.addAttribute("ProductID", ProductID);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("UnitPrice", UnitPrice);
        return "/User/Product";
    }
}
