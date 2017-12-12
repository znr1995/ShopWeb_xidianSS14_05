package com.project_management.shoppingweb.controller.Seller.ProductsManagement;


import com.project_management.shoppingweb.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;

@Controller
@RequestMapping("/Seller/ProductsManagement")
public class ProductsManagementController {
    private LinkedList<Product> products;
    private long sellerID = -1;

    @RequestMapping("ProductsManagementHandler")
    public String jumpToProductManagementMainPage(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        products = SellerSQLFunction.getInstance().getAllProducts(sellerID);
        model.addAttribute("products",products);
        model.addAttribute("SellerID",sellerID);
        return "/Seller/ProductsManagementMainPage";
    }


    @RequestMapping(value = "ProductsHandler")
    public String productHandler(HttpServletRequest request, Model model, RedirectAttributes attributes)
    {
        if(request.getParameter("delete") == null)
        {
            long productId = Long.valueOf(request.getParameter("modify"));
            return jumpToModifyProductPage(productId, model, attributes);
        }
        else
        {
            long productId = Long.valueOf(request.getParameter("delete"));
            return deleteProduct(productId, attributes);
        }
    }

    @RequestMapping(value = "ProductsHandler",params = "action=delete")
    public String deleteProduct(@RequestParam("ProductID")long productId, RedirectAttributes attributes)
    {
        for(Product curProduct : products)
        {
            if(curProduct.getProductId() == productId)
            {
                if(SellerSQLFunction.getInstance().deleteProducts(productId))
                {
                    products.remove(curProduct);
                    attributes.addAttribute("SellerID",sellerID);
                    return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
                }
            }
        }
        attributes.addAttribute("errorMessage","not find the remove products");
        return "redirect:/errorHandler";
    }

    @RequestMapping(value = "ProductsHandler", params = "action=modify")
    public String jumpToModifyProductPage(@RequestParam("ProductID")long productId, Model model,RedirectAttributes attributes)
    {
        for(Product curProduct : products)
        {
            if(curProduct.getProductId() == productId)
            {
                model.addAttribute("productName",curProduct.getProductName());
                model.addAttribute("productStock",curProduct.getProductStock());
                model.addAttribute("brandName",curProduct.getBrandName());
                model.addAttribute("productPhoto",curProduct.getProductPhoto());
                model.addAttribute("productBriefInfo",curProduct.getProductBriefInfo());
                model.addAttribute("ProductID",productId);
                model.addAttribute("SellerID",sellerID);
                return "/Seller/ModifyProductInformationPage";
            }
        }
        attributes.addAttribute("errorMessage","not find the modify product");
        return "redirect:/errorHandler";
    }


    @RequestMapping("AddProduct")
    public String jumpToAddProductPage(Model model)
    {
        model.addAttribute("SellerID",sellerID);
        return "/Seller/AddProductPage";
    }

    //返回商家主界面,脱离商品管理模块,重定向方式
    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }


}
