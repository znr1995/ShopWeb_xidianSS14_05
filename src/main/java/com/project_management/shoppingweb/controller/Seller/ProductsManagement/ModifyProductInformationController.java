package com.project_management.shoppingweb.controller.Seller.ProductsManagement;



import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Seller/ModifyProduct")
public class ModifyProductInformationController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping("ModifyInformation")
    public String addProductAndReturnBack(HttpServletRequest request,RedirectAttributes attributes)
    {
        long productID = Long.valueOf(request.getParameter("ProductID"));
        Product newProduct = sellerService.getProduct(productID);
        if(newProduct == null)
        {
            attributes.addAttribute("errorMessage","productId is wrong!");
            return "redirect:/error/errorHandler";
        }

        newProduct.setProductStock(Integer.valueOf(request.getParameter("productStock")));
        newProduct.setBrandName(request.getParameter("brandName"));
        newProduct.setProductPhoto(request.getParameter("productPhoto"));
        newProduct.setProductBriefInfo(request.getParameter("productBriefInfo"));
        newProduct.setProductName(request.getParameter("productName"));
        newProduct.setProductPrice(Double.valueOf(request.getParameter("ProductPrice")));
        attributes.addAttribute("SellerID",Long.valueOf(request.getParameter("SellerID")));
        if(sellerService.writeInProduct(newProduct))
            return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
        else
        {
            attributes.addAttribute("errorMessage","modify product information fail!");
            return "redirect:/error/errorHandler";
        }

    }

    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(HttpServletRequest request, RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",Long.valueOf(request.getParameter("SellerID")));
        return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
    }

}
