package com.project_management.shoppingweb.controller.User.Product;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductDetailController {
    @Autowired
    private ProductService productService;


    private Logger logger = Logger.getLogger(this.getClass());

    @RequestMapping("/product/{productid}")
    public  String Detail(Model model, @PathVariable("productid") Long id){

        Product product = productService.findProductByProductID(id);
        boolean isnull = false;
        if(product == null) isnull = true;
        model.addAttribute("productdetail", product);
        model.addAttribute("proisnull", isnull);
        return "/Product/ProductDetail";
    }
}
