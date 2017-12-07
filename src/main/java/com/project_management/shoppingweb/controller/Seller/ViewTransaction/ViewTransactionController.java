package com.project_management.shoppingweb.controller.Seller.ViewTransaction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Seller/ViewTransaction")
public class ViewTransactionController {
    @RequestMapping("ViewTransactionHandler")
    public String jumpToViewTransaction(@ModelAttribute("SellerID")int sellerId, Model model)
    {
        //TODO:增加模块的主页所需信息
        return "ViewTransactionMainPage";
    }
}
