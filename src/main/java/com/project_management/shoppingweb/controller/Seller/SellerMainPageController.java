package com.project_management.shoppingweb.controller.Seller;


import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SellerMainPageController {



    @Autowired
    private Seller_SellerService sellerSellerService;

    private long sellerID = -1;

    //启动主界面
    @RequestMapping("/Seller/Main")
    public String jumpToSellerMainPage(@ModelAttribute("SellerID")long sellerId, Model model, RedirectAttributes attributes)
    {
        sellerID = sellerId;
        Seller seller = sellerSellerService.getSellerById(sellerId);
        if(seller == null)
        {
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
    }

    //退出跳转
    @RequestMapping("/Seller/logout")
    public String logout()
    {
        return "login";
    }

    @RequestMapping(value = "/Seller/FastJump")
    public String jumpToOtherPage(HttpServletRequest request, RedirectAttributes attributes)
    {
        String action = request.getParameter("action");
        attributes.addAttribute("SellerID",sellerID);
        if (action.equals("ModifySellerAdvertisement")) {
            return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
        } else if (action.equals("ModifySellerInformation")) {
            return "redirect:/Seller/ModifySellerInformation/ModifySellerInformationHandler";
        } else if (action.equals("ViewIncome")) {
            return "redirect:/Seller/ViewIncome/ViewIncomeHandler";
        } else if (action.equals("ViewTransaction")) {
            return "redirect:/Seller/ViewTransaction/ViewTransactionHandler";
        } else if (action.equals("ProductsManagement")) {
            return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
        }
        return "redirect:/error/errorHandler";
    }



}
