package com.project_management.shoppingweb.controller.User;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BuyController {
    @RequestMapping(value = "/Buy",method = RequestMethod.GET)
    public String Buy(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");


        if(UserID.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/Product";
        }
        String ProductAmount = request.getParameter("ProductAmount");

        if(ProductAmount.equals("")||UnitPrice.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/Product";
        }
        if(Integer.parseInt(ProductAmount)<=0||Double.valueOf(UnitPrice)<=0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/Product";
        }

        double UP = Double.valueOf(UnitPrice);
        double PA = Double.valueOf(ProductAmount);
        double t = UP * PA;
        String Total = String.valueOf(t);


        model.addAttribute("UserID", UserID);
        model.addAttribute("ProductID", ProductID);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("UnitPrice", UnitPrice);
        model.addAttribute("ProductAmount", ProductAmount);
        model.addAttribute("Total", Total);
        return "/User/Pay";
    }
}
