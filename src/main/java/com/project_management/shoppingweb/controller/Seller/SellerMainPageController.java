package com.project_management.shoppingweb.controller.Seller;

import com.project_management.shoppingweb.service.SellerSQLFunction;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;


@RequestMapping("/Seller")
@Controller
public class SellerMainPageController {

    private SellerSQLFunction sellerSQLFunction = new SellerSQLFunction();
    private long sellerID = -1;

    //启动主界面
    @RequestMapping("Main")
    public String jumpToSellerMainPage(@ModelAttribute("sellerID")long ID, Model model)
    {
        if(ID <= 0)
            //TODO:error Handler
            return "error/page";
        //能否将对象传过去
        sellerID = ID;
        //TODO:还不确定
        model.addAttribute("seller",sellerSQLFunction.getSellerInfromation(sellerID).getSculpture());
        return "Seller/SellerMainPage";
    }

    //退出跳转
    @RequestMapping("logout")
    public String logout()
    {
        return "../index";
    }


    //跳转到对应模块,使用重定向机制
    @RequestMapping("ModifySellerAdvertisement")
    public String jumpToModifySellerAdvertisement(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    @RequestMapping("ModifySellerInformation")
    public String jumpToModifySellerInformation(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:ModifySellerInformation/ModifySellerInformationHandler";
    }

    @RequestMapping("ViewIncome")
    public String jumpToViewIncome(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:ViewIncome/ViewIncomeHandler";
    }

    @RequestMapping("ViewTranstion")
    public String jumpToTranstion(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:ViewTranstion/ViewTranstionHandler";
    }

    @RequestMapping("ProductsManagement")
    public String jumpToProductsManagement(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:ProductsManagement/ProductsManagementHandler";
    }

}
