package com.project_management.shoppingweb.controller.Seller.ProductsManagement;


import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/Seller/ProductsManagement")
public class ProductsManagementController {

    @Autowired
    private Seller_SellerService sellerSellerService;

    private List<Product> products;
    private long sellerID = -1;

    //跳转商品管理主界面前
    @RequestMapping("ProductsManagementHandler")
    public String jumpToProductManagementMainPage(@ModelAttribute("SellerID")long sellerId, Model model,RedirectAttributes attributes)
    {
        if(sellerId < 0)
        {
            attributes.addAttribute("errorMessage","shopowner id  is not valid!");
            return "redirect:/error/errorHandler";
        }

        sellerID = sellerId;
        products = sellerSellerService.getSellerProducts(sellerID);
        model.addAttribute("products",products);
        model.addAttribute("SellerID",sellerID);
        return "/Seller/ProductsManagementMainPage";
    }


    @RequestMapping(value = "ProductsHandler")
    public String productHandler(HttpServletRequest request, Model model, RedirectAttributes attributes)
    {
        if(request.getParameter("delete") == null)
        {
           try {
               long productId = Long.valueOf(request.getParameter("modify"));
               return jumpToModifyProductPage(productId, model, attributes);
           }
           catch (Exception e)
           {
               attributes.addAttribute("errorMessage",e.getMessage());
               return "redirect:/error/errorHandler";
           }

        }
        else
        {
           try {
               long productId = Long.valueOf(request.getParameter("delete"));
               return deleteProduct(productId, attributes);
           }catch (Exception e)
           {
               attributes.addAttribute("errorMessage",e.getMessage());
               return "redirect:/error/errorHandler";
           }
        }
    }

    @RequestMapping(value = "ProductsHandler",params = "action=delete")
    public String deleteProduct(@RequestParam("ProductID")long productId, RedirectAttributes attributes)
    {
        for(Product curProduct : products)
        {
            if(curProduct.getProductId() == productId)
            {
                    sellerSellerService.deleteProduct(productId);
                    products.remove(curProduct);
                    attributes.addAttribute("SellerID",sellerID);
                    return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
            }
        }

        attributes.addAttribute("errorMessage","not find the remove products");
        return "redirect:/error/errorHandler";
    }

    @RequestMapping("ModifyProduct")
    public String jumpToModifyProductPage(@RequestParam("ProductID")long productId, Model model,RedirectAttributes attributes)
    {
        for(Product curProduct : products)
        {
            if(curProduct.getProductId() == productId)
            {
                //TODO:如果需要修改 修改商品界面 的信息,从这里开始
                //type, size 没加
                model.addAttribute("productName",curProduct.getProductName());
                model.addAttribute("productStock",curProduct.getProductStock());
                model.addAttribute("brandName",curProduct.getBrandName());
                model.addAttribute("productPhoto",curProduct.getProductPhoto());
                model.addAttribute("productBriefInfo",curProduct.getProductBriefInfo());
                model.addAttribute("productPrice",curProduct.getProductPrice());
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
        return "redirect:/Seller/logout";
    }


}
