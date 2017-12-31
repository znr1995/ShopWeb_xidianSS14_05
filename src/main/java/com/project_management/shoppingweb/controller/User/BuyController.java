package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Address;
import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.service.AddressService;
import com.project_management.shoppingweb.service.SellerService;
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
public class BuyController {
    @Autowired
    private AddressService addressService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private SellerService sellerService;


    @RequestMapping(value = "/Buy",method = RequestMethod.GET)
    public String Buy(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");
        Product product = productService.findProductByProductID(Long.parseLong(ProductID));
        String ProductName = product.getProductName();
        Seller seller = sellerService.findBySellerId(Long.parseLong(ShopID));
        String SellerName = seller.getShopname();



        if(UserID.equals("-1")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }
        String ProductAmount = request.getParameter("ProductAmount");

        if(ProductAmount.equals("")||UnitPrice.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }
        int haha;
        try {
            haha = Integer.parseInt(ProductAmount);
        }
        catch (Exception e){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }
        if(haha<=0||Double.valueOf(UnitPrice)<=0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }

        double UP = Double.valueOf(UnitPrice);
        double PA = Double.valueOf(ProductAmount);
        double t = UP * PA;
        String Total = String.valueOf(t);


        model.addAttribute("UserID", UserID);
        model.addAttribute("IsFromShoppingCart", "0");
        model.addAttribute("ProductID", ProductID);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("UnitPrice", UnitPrice);
        model.addAttribute("ProductAmount", ProductAmount);
        model.addAttribute("Total", Total);
        model.addAttribute("ProductName", ProductName);
        model.addAttribute("SellerName", SellerName);

        List<Address> AddressList = new ArrayList<Address>();
        AddressList = addressService.findAllByUserId(Long.parseLong(UserID));
        if(AddressList.size() == 0)
            return "/User/PayNew";
        model.addAttribute("AddressList", AddressList);

        return "/User/PayNew";
    }
}
