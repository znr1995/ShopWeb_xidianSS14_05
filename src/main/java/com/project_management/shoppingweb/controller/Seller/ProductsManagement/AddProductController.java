package com.project_management.shoppingweb.controller.Seller.ProductsManagement;




import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.Seller.Seller_CopyFile;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

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

    @RequestMapping(value = "AddProduct")
    public String addProductAndReturnBack(@RequestParam(value = "productPhotoFile", required = false)MultipartFile file,
                                          HttpServletRequest request, RedirectAttributes attributes)
    {
        long sellerID = Long.valueOf(request.getParameter("SellerID"));
        attributes.addAttribute("SellerID",sellerID);

        //TODO:保证添加商品时候每一个商品的属性都不为空

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
        //TODO:product 属性是否全

        //判断产品名是否为空
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

        //判断库存是否为空
        if(request.getParameter("productStock").isEmpty()){
            attributes.addAttribute("errorMessage","productStock can't be null!");
            return "redirect:/error/errorHandler";
        }

        //判断库存是否为数字
        int stockLen=0;
        for (stockLen=0;stockLen<request.getParameter("productStock").length();stockLen++){
            if(!Character.isDigit(request.getParameter("productStock").charAt(stockLen))){
                attributes.addAttribute("errorMessage","productStock must be a integer&&>=0!");
                return "redirect:/error/errorHandler";
            }
        }
        //判断库存是否大于等于零
        if(Integer.valueOf(request.getParameter("productStock"))<0){
            attributes.addAttribute("errorMessage","productStock is wrong!(stock<0)");
            return "redirect:/error/errorHandler";
        }

        //判断图片是否为空
        if(file.isEmpty()){
            attributes.addAttribute("errorMessage","photo can't be null!");
            return "redirect:/error/errorHandler";
        }

        //判断价格是否为空
        if(request.getParameter("productPrice").isEmpty()){
            attributes.addAttribute("errorMessage","productPrice can't be null!");
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

        //判断价格是否为数字
        //int priceLen=0;
       // for (priceLen=0;priceLen<request.getParameter("productPrice").length();priceLen++){
           // if(!(Character.isDigit(request.getParameter("productPrice").charAt(priceLen))
             //       ||request.getParameter("productPrice").charAt(priceLen)=='.')){
           //     attributes.addAttribute("errorMessage","productPrice must be a double&&price>0!");
             //   return "redirect:/error/errorHandler";
         //   }
       // }

        //判断价格是否大于零
        if (Double.valueOf(request.getParameter("productPrice"))<=0){
            attributes.addAttribute("errorMessage","productPrice is wrong!(price <=0)");
            return "redirect:/error/errorHandler";
        }

        //判断产品简要信息是否为空
        if(request.getParameter("productBriefInfo").isEmpty()){
            attributes.addAttribute("errorMessage","productBriefInfo can't be null!");
            return "redirect:/error/errorHandler";
        }



        newProduct.setProductPhoto(Seller_CopyFile.getInstance().copyFile(file));
        newProduct.setBrandName(request.getParameter("brandName"));
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
