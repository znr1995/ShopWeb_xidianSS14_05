package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductCollection;
import com.project_management.shoppingweb.domain.ShopCollection;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_ProductCollectionService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShopCollectionService;
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
public class MyFavoriteController {
    @Autowired
    private User_ProductCollectionService productCollectionService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private User_ShopCollectionService shopCollectionService;
    @Autowired
    private SellerService sellerService;


    @RequestMapping(value = "/MyFavorite",method = RequestMethod.GET)
    public String MyFavorite(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        model.addAttribute("UserID", UserID);

        if(UserID.equals("-1")){
            String ProductID = request.getParameter("ProductID");
            String ShopID = request.getParameter("ShopID");
            String UnitPrice = request.getParameter("UnitPrice");
            Product product = productService.findProductByProductID(Long.parseLong(ProductID));
            String ProductName = product.getProductName();
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }

        model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());
        List<ProductCollection> FavoritelistP = new ArrayList<ProductCollection>();
        FavoritelistP = productCollectionService.findAllByUserId(Long.parseLong(UserID));
        List<ShopCollection> FavoriteShop = new ArrayList<ShopCollection>();
        FavoriteShop = shopCollectionService.findAllByUserId(Long.parseLong(UserID));

        if(FavoritelistP.size() != 0){
            List<FavoriteToShow> FavoriteProduct = new ArrayList<FavoriteToShow>();
            for(int i = 0; i < FavoritelistP.size(); i++){
                FavoriteToShow favoriteToShow = new FavoriteToShow();
                favoriteToShow.ID = String.valueOf(FavoritelistP.get(i).getCollectionId());
                favoriteToShow.PID = String.valueOf(FavoritelistP.get(i).getProductId());
                favoriteToShow.SID = String.valueOf(productService.findProductByProductID(FavoritelistP.get(i).getProductId()).getSellerId());
                Product product = productService.findProductByProductID(FavoritelistP.get(i).getProductId());
                favoriteToShow.NameP = product.getProductName();
                favoriteToShow.NameS = sellerService.findBySellerId(Long.parseLong(favoriteToShow.SID)).getShopname();
                FavoriteProduct.add(favoriteToShow);
            }
            model.addAttribute("ProductList", FavoriteProduct);
        }




        //------------------------------------------------------------------------------------------------------------------------------------------------


        if(FavoriteShop.size() != 0){
            List<FavoriteToShow> FavoriteShops = new ArrayList<FavoriteToShow>();
            for(int i = 0; i < FavoriteShop.size(); i++){
                FavoriteToShow favoriteToShow = new FavoriteToShow();
                favoriteToShow.ID = String.valueOf(FavoriteShop.get(i).getId());
                favoriteToShow.SID = String.valueOf(FavoriteShop.get(i).getSellerId());
                favoriteToShow.NameS = sellerService.findBySellerId(FavoriteShop.get(i).getSellerId()).getShopname();
                FavoriteShops.add(favoriteToShow);
            }
            model.addAttribute("ShopList", FavoriteShops);
        }

        return "/User/FavoriteNew";

    }
}

class FavoriteToShow{
    public String ID;
    public String PID;
    public String SID;
    public String NameP;
    public String NameS;
}
