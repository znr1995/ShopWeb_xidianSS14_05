package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.ShopCollection;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShopCollectionService;
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
public class AddIntoFavoriteSController {
    @Autowired
    private User_ShopCollectionService shopCollectionService;
    @Autowired
    private UserService userService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "/AddIntoFavoriteS", method = RequestMethod.POST)
    public String AddIntoFavoriteS(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String SellerID = request.getParameter("SellerID");

        if(UserID.equals("-1")){
            List<Product> products =
                    productService.findAllBySellerID(Long.parseLong(SellerID));
            boolean pro_isnull = false;
            if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
            long UserIDD = Long.parseLong(UserID);
            model.addAttribute("UserID", UserID);
            model.addAttribute("products",products);
            model.addAttribute("ShopID", SellerID);
            Seller seller = sellerService.findBySellerId(Long.parseLong(SellerID));
            model.addAttribute("seller", seller);
            model.addAttribute("pro_isnull", pro_isnull);
            if(UserIDD == -1){
                model.addAttribute("UserName", "UserName");
            }
            else{
                User user = userService.findByUserId(UserIDD);
                model.addAttribute("UserName", user.getUsername());
            }
            return "/User/sellersproduct";
        }

        List<ShopCollection> GlobalFavorites = new ArrayList<ShopCollection>();
        GlobalFavorites = shopCollectionService.findAllByUserId(Long.parseLong(UserID));
        for(int i = 0; i < GlobalFavorites.size(); i++){
            if(GlobalFavorites.get(i).getSellerId() == Long.parseLong(SellerID)){
                List<Product> products =
                        productService.findAllBySellerID(Long.parseLong(SellerID));
                boolean pro_isnull = false;
                if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
                long UserIDD = Long.parseLong(UserID);
                model.addAttribute("UserID", UserID);
                model.addAttribute("products",products);
                model.addAttribute("ShopID", SellerID);
                Seller seller = sellerService.findBySellerId(Long.parseLong(SellerID));
                model.addAttribute("seller", seller);
                model.addAttribute("pro_isnull", pro_isnull);
                if(UserIDD == -1){
                    model.addAttribute("UserName", "UserName");
                }
                else{
                    User user = userService.findByUserId(UserIDD);
                    model.addAttribute("UserName", user.getUsername());
                }
                return "/User/sellersproduct";
            }
        }

        ShopCollection shopCollection = new ShopCollection();
        shopCollection.setSellerId(Long.parseLong(SellerID));
        shopCollection.setUserId(Long.parseLong(UserID));

        shopCollectionService.save(shopCollection);

        List<Product> products =
                productService.findAllBySellerID(Long.parseLong(SellerID));
        boolean pro_isnull = false;
        if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
        long UserIDD = Long.parseLong(UserID);
        model.addAttribute("UserID", UserID);
        model.addAttribute("products",products);
        model.addAttribute("ShopID", SellerID);
        Seller seller = sellerService.findBySellerId(Long.parseLong(SellerID));
        model.addAttribute("seller", seller);
        model.addAttribute("pro_isnull", pro_isnull);
        if(UserIDD == -1){
            model.addAttribute("UserName", "UserName");
        }
        else{
            User user = userService.findByUserId(UserIDD);
            model.addAttribute("UserName", user.getUsername());
        }
        return "/User/sellersproduct";
    }
}
