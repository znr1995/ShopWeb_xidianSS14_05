package com.project_management.shoppingweb.controller.Seller.ProductsManagement;




import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Seller/AddProduct")

public class AddProductController {


    Boolean judgeString(String str)
    {
        if(str == null || str.equals(""))
            return false;
        return true;
    }

    @RequestMapping("AddProduct")
    public String addProductAndReturnBack(HttpServletRequest request, RedirectAttributes attributes)
    {

        int sellerID = Integer.valueOf(request.getParameter("SellerID"));
        attributes.addAttribute("SellerID",sellerID);

        if(!(judgeString(request.getParameter("productStock")) &&
                judgeString(request.getParameter("brandName")) &&
                judgeString(request.getParameter("isOnSale")) &&
                judgeString(request.getParameter("productPhoto")) &&
                judgeString(request.getParameter("productMarketPrice")) &&
                judgeString(request.getParameter("productBriefInfo")) &&
                judgeString(request.getParameter("productName"))
        ))
        {
            attributes.addAttribute("errorMessage","every attribute must be add ! not allow empty or null");
            return "redirect:/error/errorHandler";
        }

        int productStock;
        boolean isOnSale;
        double productMarkPrice;
        Product newProduct = new Product();
        try{
            productStock = Integer.valueOf(request.getParameter("productStock"));
        }
        catch (Exception e)
        {
            attributes.addAttribute("errorMessage","Stock format wrong :" +e.getMessage());
            return "redirect:/error/errorHandler";
        }
        try
        {
            isOnSale = Boolean.valueOf(request.getParameter("isOnSale"));
        }
        catch (Exception e)
        {
            attributes.addAttribute("errorMessage","isOnSale format wrong :" +e.getMessage());
            return "redirect:/error/errorHandler";
        }

        try {
            productMarkPrice = Double.valueOf(request.getParameter("productMarketPrice"));
        }
        catch (Exception e)
        {
            attributes.addAttribute("errorMessage","productMarkedPrice format wrong :" +e.getMessage());
            return "redirect:/error/errorHandler";
        }

        newProduct.setProductStock(productStock);
        newProduct.setBrandName(request.getParameter("brandName"));
        newProduct.setIsOnSale( isOnSale);
        newProduct.setProductPhoto(request.getParameter("productPhoto"));
        newProduct.setProductMarketPrice(productMarkPrice);
        newProduct.setProductBriefInfo(request.getParameter("productBriefInfo"));
        newProduct.setProductName(request.getParameter("productName"));


        if(SellerSQLFunction.getInstance().createProductInformation(sellerID,newProduct))
        {
            return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
        }
        else
        {
            attributes.addAttribute("errorMessage","create Product fail!");
            return "redirect:/error/errorHandler";
        }

    }

    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(HttpServletRequest request, RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",Integer.valueOf(request.getParameter("SellerID")));
        return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
    }


}
