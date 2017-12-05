package com.project_management.shoppingweb.controller.Seller.ProductsManagement;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ProductInformation;
import com.project_management.shoppingweb.service.SellerSQLFunction;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("Seller/ProductsManagement")
public class ProductsManagementHandler {

    private SellerSQLFunction sellerSQLFunction =new SellerSQLFunction();

    private LinkedList<ProductInformation> products;
    private long sellerID = -1;

    @RequestMapping("ProductsManagementHandler")
    public String jumpToProductManagementMainPage(@ModelAttribute("SellerID") long sellerId, Model model)
    {
         sellerID = sellerId;
         products = sellerSQLFunction.getAllProducts(sellerID);
         LinkedList<String> names = new LinkedList<String>();
         LinkedList<Long> nums = new LinkedList<Long>();
         LinkedList<Double> prices = new LinkedList<Double>();
         LinkedList<Long> productIDs = new LinkedList<Long>();
         for(ProductInformation curProduct : products)
         {
             nums.add(curProduct.getProductNumber());
             names.add(curProduct.getProductName());
             prices.add(curProduct.getProductPrice());
             productIDs.add(curProduct.getProductId());
         }
        //TODO:这里涉及条目,该用什么格式,有待商榷

         model.addAttribute("names",names);
         model.addAttribute("nums",nums);
         model.addAttribute("prices",prices);
         model.addAttribute("produdctIDs",productIDs);
         model.addAttribute("SellerID",sellerID);
        return "Seller/ProductsManagementMainPage";
    }

    @RequestMapping("DeleteProduct")
    //TODO:boolean is wrong
    public boolean deleteProduct(HttpServletRequest request, Model model)
    {
        long productId = Long.valueOf(request.getParameter("proudctID"));
        for(ProductInformation curProduct : products)
        {
            if(curProduct.getProductId() == productId)
            {
               if(sellerSQLFunction.deleteProducts(productId))
               {
                   products.remove(curProduct);
                   return true;
               }
            }
        }
        return false;
    }

    @RequestMapping("ModifyProduct")
    public String jumpToModifyProductPage(HttpServletRequest request, Model model)
    {
        //获得需要修改的商品ID
        long productId = Long.valueOf(request.getParameter("proudctID"));
        for(ProductInformation curProduct : products)
        {
            if(curProduct.getProductId() == productId)
            {
                model.addAttribute("SellerID",sellerID);
                model.addAttribute("ProductId",curProduct.getProductId());
                model.addAttribute("JSONStr",curProduct.getJSONStr());
                model.addAttribute("ProductName",curProduct.getProductName());
                model.addAttribute("ProductNote",curProduct.getProductNote());
                model.addAttribute("ProductNumber",curProduct.getProductNumber());
                model.addAttribute("ProductPrice",curProduct.getProductPrice());
                model.addAttribute("Producture",curProduct.getProductPicture());
                return "Seller/ModifyProductInformationPage";
            }
        }
        //TODO:error Handler
        return "error/NotFindProduct";

    }

    @RequestMapping("AddProduct")
    public String jumpToAddProductPage(Model model)
    {
        model.addAttribute("SellerID",sellerID);
        return "Seller/AddProductPage";
    }

    //返回商家主界面,脱离商品管理模块,重定向方式
    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:Seller/Main";
    }

    //快捷导航部分
    //跳转到对应模块,使用重定向机制
    @RequestMapping("ModifySellerAdvertisement")
    public String jumpToModifySellerAdvertisement(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",sellerID);
        return "redirect:../ModifySellerAdvertisement/ModifySellerAdvertisementHandler";
    }

    @RequestMapping("ModifySellerInformation")
    public String jumpToModifySellerInformation(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",sellerID);
        return "redirect:../ModifySellerInformation/ModifySellerInformationHandler";
    }

    @RequestMapping("ViewIncome")
    public String jumpToViewIncome(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",sellerID);
        return "redirect:../ViewIncome/ViewIncomeHandler";
    }

    @RequestMapping("ViewTranstion")
    public String jumpToTranstion(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",sellerID);
        return "redirect:../ViewTranstion/ViewTranstionHandler";
    }

    @RequestMapping("ProductsManagement")
    public String jumpToProductsManagement(HttpServletRequest request,RedirectAttributes attributes)
    {
        attributes.addAttribute("sellerID",sellerID);
        return "redirect:../ProductsManagement/ProductsManagementHandler";
    }
}
