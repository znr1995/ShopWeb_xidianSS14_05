package com.project_management.shoppingweb.controller.Seller.ProductsManagement;



import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.Seller_CopyFile;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/Seller/ModifyProduct")
public class ModifyProductInformationController {

    @Autowired
    private Seller_SellerService sellerSellerService;


    @RequestMapping(value = "ModifyInformation", method = RequestMethod.POST)
    public String addProductAndReturnBack(@RequestParam(value = "productPhoto", required = false)MultipartFile file,
                                          HttpServletRequest request, RedirectAttributes attributes)
    {
        long productID = Long.valueOf(request.getParameter("ProductID"));
        Product newProduct = sellerSellerService.getProduct(productID);
        if(newProduct==null)
        {
            attributes.addAttribute("errorMessage","productId is wrong!");
            return "redirect:/error/errorHandler";
        }
        long sellerID = Long.valueOf(request.getParameter("SellerID"));
        //TODO:保证每一个值都不能为空,并且合法
        //商品名非空判断
        if(request.getParameter("productName").isEmpty()){
            attributes.addAttribute("errorMessage","productName can't be null!");
            return "redirect:/error/errorHandler";
        }

        //判断库存为大于等于零的整数
        try{
            Integer.valueOf(request.getParameter("productStock"));
        }
        catch (Exception e){
            attributes.addAttribute("errorMessage","productStock must be a integer&&>=0!");
            return "redirect:/error/errorHandler";
        }
        if(Integer.valueOf(request.getParameter("productStock"))<0){
            attributes.addAttribute("errorMessage","productStock is wrong!(stock <0)");
            return "redirect:/error/errorHandler";
        }

        //判断价格为大于零的double
        try{
            Double.valueOf(request.getParameter("productPrice"));
        }
        catch (Exception e){
            attributes.addAttribute("errorMessage","productPrice must be a double&&>0!");
            return "redirect:/error/errorHandler";
        }
        if (Double.valueOf(request.getParameter("productPrice"))<=0){
            attributes.addAttribute("errorMessage","productPrice is wrong!(price <=0.0)");
            return "redirect:/error/errorHandler";
        }

        //判断商品简介非空
        if(request.getParameter("productBriefInfo").isEmpty()){
            attributes.addAttribute("errorMessage","productBriefInfo can't be null!");
            return "redirect:/error/errorHandler";
        }
        //if(file.isEmpty()){
        //    attributes.addAttribute("errorMessage","photo can't be null!");
         //   return "redirect:/error/errorHandler";
       // }

        //if(file != null && file.getSize() > 0)
            newProduct.setProductPhoto(Seller_CopyFile.getInstance().copyFile(file));
        newProduct.setProductStock(Integer.valueOf(request.getParameter("productStock")));
        newProduct.setBrandName(request.getParameter("brandName"));
        newProduct.setProductBriefInfo(request.getParameter("productBriefInfo"));
        newProduct.setProductName(request.getParameter("productName"));
        newProduct.setProductPrice(Double.valueOf(request.getParameter("productPrice")));


        //product sellerId 不需要修改
        attributes.addAttribute("SellerID",sellerID);
        if(sellerSellerService.writeInProduct(newProduct))
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
