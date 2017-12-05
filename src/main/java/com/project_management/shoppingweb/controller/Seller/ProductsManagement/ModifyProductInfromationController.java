package com.project_management.shoppingweb.controller.Seller.ProductsManagement;

import com.project_management.shoppingweb.domain.ProductInformation;
import com.project_management.shoppingweb.service.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("Seller/ModifyProduct")
public class ModifyProductInfromationController {

    private SellerSQLFunction sellerSQLFunction = new SellerSQLFunction();


    @RequestMapping("ModifyInfromation")
    public String addProductAndReturnBack(HttpServletRequest request,Model model)
    {

        if(!(request.getParameter("ProductID") != null &&
                request.getParameter("SellerID") != null &&
                request.getParameter("ProductName") != null &&
                request.getParameter("ProductNumber") != null &&
                request.getParameter("ProductPrice") != null &&
                request.getParameter("JSONStr") != null &&
                request.getParameter("ProductPiction") != null &&
                request.getParameter("ProductNote") != null ))
            //属性不全,有bug
            return "error/code1";

        //(long productId, long seller, String productName, long productNumber, long productPrice, String JSONStr, String productPicture, String productNote)
        ProductInformation newPrroduct = new ProductInformation(
                Long.valueOf(request.getParameter("ProductID")),
                Long.valueOf(request.getParameter("SellerID")),
                request.getParameter("ProductName"),
                Long.valueOf( request.getParameter("ProductNumber")),
                Double.valueOf(request.getParameter("ProductPrice")),
                request.getParameter("JSONStr"),
                request.getParameter("ProductPiction"),
                request.getParameter("ProductNote")
                );
        model.addAttribute("SellerID",Long.valueOf(request.getParameter("SellerID")));
        sellerSQLFunction.createProductInformation(Long.valueOf(request.getParameter("SellerID")),newPrroduct);
        return "../ProductsManagement/ProductsManagementHandler";

    }

    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(HttpServletRequest request,Model model)
    {
        model.addAttribute("SellerID",Long.valueOf(request.getParameter("SellerID")));
        return "../ProductsManagement/ProductsManagementHandler";
    }

    @RequestMapping("Cancel")
    public String cancel(HttpServletRequest request,Model model)
    {
        model.addAttribute("SellerID",Long.valueOf(request.getParameter("SellerID")));
        return "../ProductsManagement/ProductsManagementHandler";
    }

    //快捷导航部分
    @RequestMapping("ModifySellerAdvertisement")
    public String jumpToModifySellerAdvertisement(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",Long.valueOf(request.getParameter("sellerID")));
        return "redirect:../ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    @RequestMapping("ModifySellerInformation")
    public String jumpToModifySellerInformation(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",Long.valueOf(request.getParameter("sellerID")));
        return "redirect:../ModifySellerInformation/ModifySellerInformationHandler";
    }

    @RequestMapping("ViewIncome")
    public String jumpToViewIncome(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",Long.valueOf(request.getParameter("sellerID")));
        return "redirect:../ViewIncome/ViewIncomeHandler";
    }

    @RequestMapping("ViewTranstion")
    public String jumpToTranstion(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",Long.valueOf(request.getParameter("sellerID")));
        return "redirect:../ViewTranstion/ViewTranstionHandler";
    }

    @RequestMapping("ProductsManagement")
    public String jumpToProductsManagement(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",Long.valueOf(request.getParameter("sellerID")));
        return "redirect:../ProductsManagement/ProductsManagementHandler";
    }
}
