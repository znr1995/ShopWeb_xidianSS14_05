package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyShoppingCartController {
    @Autowired
    private User_ShoppingCartService userShoppingCartService;
    @RequestMapping(value = "/MyShoppingCart", method = RequestMethod.GET)
    public String MyShoppingCart(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");


        if(UserID.equals("")){
            String ProductID = request.getParameter("ProductID");
            String ShopID = request.getParameter("ShopID");
            String UnitPrice = request.getParameter("UnitPrice");

            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/Product";
        }





        /*用了假数据类，到时候要修改*/
        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = userShoppingCartService.findAllByUserId(Long.parseLong(UserID));


        if(GlobalShoppingCart.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/ShoppingCart";
        }

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        List<String> DateList = new ArrayList<String>();
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            DateList.add(sdf.format(GlobalShoppingCart.get(i).getCreatetime()));
        }

        List<String> AmountList = new ArrayList<String>();
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            AmountList.add(String.valueOf(GlobalShoppingCart.get(i).getProductAmount()));
        }
        List<String> ProductIDList = new ArrayList<String>();
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            ProductIDList.add(String.valueOf(GlobalShoppingCart.get(i).getProductId()));
        }



        model.addAttribute("UserID", UserID);
        model.addAttribute("DateList", DateList);
        model.addAttribute("AmountList", AmountList);
        model.addAttribute("ProductList", ProductIDList);

        return "/User/ShoppingCart";


    }
}
