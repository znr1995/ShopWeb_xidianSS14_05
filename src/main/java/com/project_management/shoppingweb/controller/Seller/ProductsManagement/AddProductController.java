package com.project_management.shoppingweb.controller.Seller.ProductsManagement;




import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/Seller/AddProduct")

public class AddProductController {

    @Autowired
    private Seller_SellerService sellerSellerService;

    Boolean judgeString(String str)
    {
        if(str == null || str.equals(""))
            return false;
        return true;
    }

    @RequestMapping("AddProduct")
    public String addProductAndReturnBack(HttpServletRequest request, RedirectAttributes attributes)
    {

        long sellerID = Long.valueOf(request.getParameter("SellerID"));
        attributes.addAttribute("SellerID",sellerID);

//        if(!(judgeString(request.getParameter("productStock")) &&
//                judgeString(request.getParameter("brandName")) &&
//                judgeString(request.getParameter("productPhoto")) &&
//                judgeString(request.getParameter("productPrice")) &&
//                judgeString(request.getParameter("productBriefInfo")) &&
//                judgeString(request.getParameter("productName"))
//        ))
//        {
//            attributes.addAttribute("errorMessage","every attribute must be add ! not allow empty or null");
//            return "redirect:/error/errorHandler";
//        }

        long productStock;
        double productMarkPrice;
        Product newProduct = new Product();
//        try{
//            productStock = Long.valueOf(request.getParameter("productStock"));
//        }
//        catch (Exception e)
//        {
//            attributes.addAttribute("errorMessage","Stock format wrong :" +e.getMessage());
//            return "redirect:/error/errorHandler";
//        }
//
//
//        try {
//            productMarkPrice = Double.valueOf(request.getParameter("productPrice"));
//        }
//        catch (Exception e)
//        {
//            attributes.addAttribute("errorMessage","productPrice format wrong :" +e.getMessage());
//            return "redirect:/error/errorHandler";
//        }

        //TODO:product 属性是否全
        newProduct.setBrandName(request.getParameter("brandName"));
        newProduct.setProductPhoto(request.getParameter("productPhoto"));
        newProduct.setProductBriefInfo(request.getParameter("productBriefInfo"));
        newProduct.setProductName(request.getParameter("productName"));
        newProduct.setSellerId(Long.valueOf(request.getParameter("SellerID")));
        newProduct.setProductStock(Integer.valueOf(request.getParameter("productStock")));
        newProduct.setProductPrice(Double.valueOf(request.getParameter("productPrice")));

        if(sellerSellerService.writeInProduct(newProduct))
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
        attributes.addAttribute("SellerID",Long.valueOf(request.getParameter("SellerID")));
        return "redirect:/Seller/ProductsManagement/ProductsManagementHandler";
    }


}
