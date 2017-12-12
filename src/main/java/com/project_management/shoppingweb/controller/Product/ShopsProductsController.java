package com.project_management.shoppingweb.controller.Product;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.ProductService;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopsProductsController {

    @Autowired
    private ProductService productService;

    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/{sellerid}")//商铺内商品页面
    public String ShopsProduct(Model model, @PathVariable("sellerid") Long sellerid){
        List<Product> products =
               productService.findAllBySellerID(sellerid);
        boolean pro_isnull = false;
        if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
        model.addAttribute("products",products);
        model.addAttribute("ShopID", sellerid);
        model.addAttribute("pro_isnull", pro_isnull);
        return "/Product/sellersproduct";
    }

}
