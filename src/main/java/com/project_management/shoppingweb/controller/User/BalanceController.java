package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.*;
import com.project_management.shoppingweb.service.AddressService;
import com.project_management.shoppingweb.service.SellerService;
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
public class BalanceController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "balance", method = RequestMethod.POST)
    public String Balance(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShoppingCartID = request.getParameter("ShoppingCartID");

        User user = userService.findByUserId(Long.parseLong(UserID));

        List<ShoppingCart> shoppingCarts = new ArrayList<ShoppingCart>();
        shoppingCarts = shoppingCartService.findAllByShoppingcartId(Long.parseLong(ShoppingCartID));
        Product product = productService.findProductByProductID(shoppingCarts.get(0).getProductId());
        if(product == null){
            model.addAttribute("UserID", UserID);
            model.addAttribute("UserName", user.getUsername());
            return "/User/productoops";
        }

        if(shoppingCarts.get(0).getProductAmount() > product.getProductStock()){

            List<ShoppingCart> ShoppingCartList = new ArrayList<ShoppingCart>();
            ShoppingCartList = shoppingCartService.findAllByShoppingcartId(Long.parseLong(ShoppingCartID));

            if(ShoppingCartList.size() != 0){
                String Amount = String.valueOf(ShoppingCartList.get(0).getProductAmount());
                model.addAttribute("Amount", Amount);
            }


            model.addAttribute("UserID", UserID);
            model.addAttribute("ShoppingCartID", ShoppingCartID);
            model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());

            return "/User/ShoppingCartAmountNew";


        }
        model.addAttribute("UserID", UserID);
        model.addAttribute("IsFromShoppingCart", "1");
        if(shoppingCarts.size() != 0) {

            model.addAttribute("ProductID", product.getProductId());
            Seller seller = sellerService.findBySellerId(product.getSellerId());
            model.addAttribute("ShopID", seller.getSellerId());
            model.addAttribute("UnitPrice", product.getProductPrice());
            model.addAttribute("ProductAmount", shoppingCarts.get(0).getProductAmount());

            model.addAttribute("UserName", user.getUsername());

            double UP = product.getProductPrice();
            double PA = shoppingCarts.get(0).getProductAmount();
            double t = UP * PA;
            String Total = String.valueOf(t);

            model.addAttribute("Total", Total);
            model.addAttribute("ProductName", product.getProductName());
            model.addAttribute("SellerName", seller.getShopname());
            model.addAttribute("UserName", userService.findByUserId(Long.parseLong(UserID)).getUsername());

            List<Address> AddressList = new ArrayList<Address>();
            AddressList = addressService.findAllByUserId(Long.parseLong(UserID));
            if(AddressList.size() == 0)
                return "/User/PayNew";
            model.addAttribute("AddressList", AddressList);
            return "/User/PayNew";
        }

        return "/User/PayNew";
    }
}
