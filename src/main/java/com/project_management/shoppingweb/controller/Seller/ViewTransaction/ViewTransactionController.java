package com.project_management.shoppingweb.controller.Seller.ViewTransaction;

import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Seller/ViewTransaction")
public class ViewTransactionController {

    @Autowired
    private Seller_SellerService seller_sellerService;

    @RequestMapping("ViewTransactionHandler")
    public String jumpToViewTransaction(@ModelAttribute("SellerID")long sellerId, Model model)
    {


        //TODO:增加模块的主页所需信息
        return "ViewTransactionMainPage";
    }
}
