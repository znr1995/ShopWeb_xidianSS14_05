package com.project_management.shoppingweb.controller.User;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Start {
    @RequestMapping(value = "/Start", method = RequestMethod.GET)
    public String start(HttpServletRequest request, Model model){


        String UserID = request.getParameter("UserID");
        String ProductID = request.getParameter("ProductID");
        //System.out.println(ProductID);
        String UnitPrice = request.getParameter("Price");
        String ShopID = request.getParameter("ShopID");
        String ProductName = request.getParameter("ProductName");

        model.addAttribute("ProductID", ProductID);
        model.addAttribute("UnitPrice", UnitPrice);
        model.addAttribute("ShopID", ShopID);
        model.addAttribute("ProductName", ProductName);
        model.addAttribute("UserID", UserID);

        return "/User/productdetial";
    }
}
