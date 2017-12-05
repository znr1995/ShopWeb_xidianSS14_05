package com.project_management.shoppingweb.controller.Seller.ViewTranstion;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Seller")
public class ViewTranstionHandler {

    @RequestMapping("ViewTranstionHandler")
    public String jumpToViewTranstion(long sellerId, Model model)
    {
        //TODO:增加模块的主页所需信息
        return "ViewTranstionMainPage";
    }
}
