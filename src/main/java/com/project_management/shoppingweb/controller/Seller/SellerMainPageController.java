package com.project_management.shoppingweb.controller.Seller;


import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.hibernate.boot.model.relational.AuxiliaryDatabaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SellerMainPageController {



    @Autowired
    private Seller_SellerService sellerSellerService;

    private long sellerID = -1;
    @RequestMapping("/Seller/login")
    public String login(){ return "/Seller/login"; }
    @RequestMapping("/Seller/register")
    public String register() { return "/Seller/register";}
    @RequestMapping("/Seller/pay")
    public String pay(){ return "/Seller/pay"; }

    //启动主界面
    @RequestMapping("/Seller/Main")
    public String jumpToSellerMainPage(@ModelAttribute("SellerID")long sellerId,Model model, RedirectAttributes attributes)
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


    @RequestMapping(value = "/Seller/FastJump/ModifySellerAdvertisementHandler")
    public String jumpToModifySellerAdvertisementHandler(HttpServletRequest request, RedirectAttributes attributes)
    {
        long sellerId = -1;
        try {
            sellerId=Long.valueOf(request.getParameter("SellerID"));
        }catch (Exception e){
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }
        attributes.addAttribute("SellerID",sellerId);
        return "redirect:/Seller/ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }
    @RequestMapping(value = "/Seller/FastJump/ModifySellerInformationHandler")
    public String jumpToModifySellerInformationHandler(HttpServletRequest request, RedirectAttributes attributes)
    { long sellerId = -1;
        try {
            sellerId=Long.valueOf(request.getParameter("SellerID"));
        }catch (Exception e){
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }
        attributes.addAttribute("SellerID",sellerId);
        return "redirect:/Seller/ModifySellerInformation/ModifySellerInformationHandler";
    }
    @RequestMapping(value = "/Seller/FastJump/ViewIncomeHandler")
    public String jumpToViewIncomeHandler(HttpServletRequest request, RedirectAttributes attributes)
    {
        long sellerId = -1;
        try {
            sellerId=Long.valueOf(request.getParameter("SellerID"));
        }catch (Exception e){
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }
        attributes.addAttribute("SellerID",sellerId);
        return "redirect:/Seller/ViewIncome/ViewIncomeHandler";
    }

    @RequestMapping(value = "/Seller/FastJump/ViewTransactionHandler")
    public String jumpToViewTransactionHandler(HttpServletRequest request, RedirectAttributes attributes)
    {
        long sellerId = -1;
        try {
            sellerId=Long.valueOf(request.getParameter("SellerID"));
        }catch (Exception e){
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }
        attributes.addAttribute("SellerID",sellerId);
        return "redirect:/Seller/ViewTransaction/ViewTransactionHandler";
    }
    @RequestMapping(value = "/Seller/FastJump/ProductsManagementHandler")
    public String jumpToProductsManagementHandler(HttpServletRequest request, RedirectAttributes attributes)
    {
        long sellerId = -1;
        try {
            sellerId=Long.valueOf(request.getParameter("SellerID"));
        }catch (Exception e){
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }
        attributes.addAttribute("SellerID",sellerId);
        return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
    }




}
