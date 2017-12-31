package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_LoginService;
import com.project_management.shoppingweb.service.User.User_ProductCollectionService;
import com.project_management.shoppingweb.service.User.User_ProductService;
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
public class AddIntoFavoritePController {
    @Autowired
    private User_ProductCollectionService productCollectionService;
    @Autowired
    private User_ProductService productService;



    @RequestMapping(value = "/AddIntoFavoriteP",method = RequestMethod.GET)
    public String AddIntoFavoriteP(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");
        Product product = productService.findProductByProductID(Long.parseLong(ProductID));
        String ProductName = product.getProductName();

        if(UserID.equals("-1")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("ProductID", ProductID);
            System.out.println("no");
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);

            return "/User/productdetial";
        }

        List<ProductCollection> GlobalFavorites = new ArrayList<ProductCollection>();
        GlobalFavorites = productCollectionService.findAllByUserId(Long.parseLong(UserID));
        for(int i = 0; i < GlobalFavorites.size(); i++){
            if(GlobalFavorites.get(i).getProductId()==Long.parseLong(ProductID)){
                model.addAttribute("UserID", UserID);
                model.addAttribute("ShopID", ShopID);
                model.addAttribute("ProductID", ProductID);
                System.out.println("no");
                model.addAttribute("UnitPrice", UnitPrice);
                model.addAttribute("ProductName", ProductName);
                return "/User/productdetial";
            }
        }

        ProductCollection favorite = new ProductCollection();
        favorite.setUserId(Long.parseLong(UserID));
        favorite.setProductId(Long.parseLong(ProductID));

        productCollectionService.save(favorite);

        System.out.println("ok");
        model.addAttribute("UserID", UserID);
        model.addAttribute("ProductID", ProductID);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("UnitPrice", UnitPrice);
        model.addAttribute("ProductName", ProductName);
        return "/User/productdetial";
    }
}
