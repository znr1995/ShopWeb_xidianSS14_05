package com.project_management.shoppingweb.controller.Seller.ProductsManagement;



import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

@Controller
@RequestMapping("/Seller/ModifyProduct")
public class ModifyProductInformationController {

    @RequestMapping("ModifyInformation")
    public String addProductAndReturnBack(HttpServletRequest request, RedirectAttributes attributes)
    {
        Product newProduct = null;
        int productID = Integer.valueOf(request.getParameter("ProductID"));
        LinkedList<Product> products = SellerSQLFunction.getInstance().getAllProducts(Integer.valueOf(request.getParameter("SellerID")));
        for(Product product : products)
        {
            if(product.getProductId() == productID)
            {
                newProduct = product;
                break;
            }
        }
        newProduct.setProductStock(Integer.valueOf(request.getParameter("productStock")));
        newProduct.setBrandName(request.getParameter("brandName"));
        newProduct.setIsOnSale( Boolean.valueOf(request.getParameter("isOnSale")));
        newProduct.setProductPhoto(request.getParameter("productPhoto"));
        newProduct.setProductMarketPrice(Double.valueOf(request.getParameter("productMarketPrice")));
        newProduct.setProductBriefInfo(request.getParameter("productBriefInfo"));
        newProduct.setProductName(request.getParameter("productName"));

        attributes.addAttribute("SellerID",Integer.valueOf(request.getParameter("SellerID")));
        if(SellerSQLFunction.getInstance().changedProducts(newProduct))
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
        attributes.addAttribute("SellerID",Integer.valueOf(request.getParameter("SellerID")));
        return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
    }

}
