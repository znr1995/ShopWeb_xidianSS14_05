package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.service.User.User_ProductCollectionService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FavoriteInHomeController {
    @Autowired
    private User_ProductCollectionService productCollectionService;
    @Autowired
    private User_ProductService productService;
    @RequestMapping(value = "/FavoriteInHome", method = RequestMethod.GET)

    public String FavoriteInHome(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");

        if(UserID.equals("-1")){
//            String ProductID = request.getParameter("ProductID");
//            String ShopID = request.getParameter("ShopID");
//            String UnitPrice = request.getParameter("UnitPrice");
//            Product product = productService.findProductByProductID(Long.parseLong(ProductID));
//            String ProductName = product.getProductName();
//            model.addAttribute("UserID", UserID);
//            model.addAttribute("ProductID", ProductID);
//            model.addAttribute("ShopID", ShopID);
//            model.addAttribute("UnitPrice", UnitPrice);
//            model.addAttribute("ProductName", ProductName);
            return "/User/login";
        }

        List<ProductCollection> FavoritelistP = new ArrayList<ProductCollection>();
        FavoritelistP = productCollectionService.findAllByUserId(Long.parseLong(UserID));


        if(FavoritelistP.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/Favorite";
        }


        List<FavoriteToShow> FavoriteProduct = new ArrayList<FavoriteToShow>();
        for(int i = 0; i < FavoritelistP.size(); i++){
            FavoriteToShow favoriteToShow = new FavoriteToShow();
            favoriteToShow.ID = String.valueOf(FavoritelistP.get(i).getProductId());
            Product product = productService.findProductByProductID(FavoritelistP.get(i).getProductId());
            favoriteToShow.Name = product.getProductName();
            FavoriteProduct.add(favoriteToShow);
        }
        model.addAttribute("ProductList", FavoriteProduct);

        model.addAttribute("UserID", UserID);
        return "/User/Favorite";
    }
}

