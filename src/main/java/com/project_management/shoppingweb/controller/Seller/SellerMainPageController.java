package com.project_management.shoppingweb.controller.Seller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;


@Controller
public class SellerMainPageController {

    private int sellerID = -1;


    @RequestMapping(value="Seller/hello", method= RequestMethod.GET)
    public boolean Hai(HttpServletRequest request , Model model){
        model.addAttribute("name",request.getParameter("oname"));
        model.addAttribute("bname",request.getParameter("bname"));
        return true;
    }



    //启动主界面
    @RequestMapping("/Seller/Main")
    public String jumpToSellerMainPage(@ModelAttribute("SellerID")int sellerId, Model model)
    {
        sellerID = sellerId;
        System.out.println(sellerID);
        model.addAttribute("SellerPath","path.png");
        model.addAttribute("SellerID",sellerID);
        model.addAttribute("SellerName","znr");
        return "/Seller/SellerMainPage";
    }

    //退出跳转
    @RequestMapping("/Seller/logout")
    public String logout()
    {
        return "../index";
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
        return "/error/page";
    }



}
