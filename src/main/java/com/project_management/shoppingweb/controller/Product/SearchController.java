package com.project_management.shoppingweb.controller.Product;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.service.ProductService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;


@Controller

public class SearchController {

    @Autowired
    private ProductService productService;

    private Logger logger = Logger.getLogger(this.getClass());
    //通过商品名查询商品
    //eg: /search/name=pants 查询pants
    @RequestMapping(value = "/search/name", method = RequestMethod.GET)
    public String searchByName(Model model, HttpServletRequest str){
        List<Product> products = productService.findAllByNameMatch(str.getParameter("productname"));
        boolean pro_isnull = false;
        if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
        model.addAttribute("products",products);
        model.addAttribute("pro_isnull", pro_isnull);
        return "/Product/search";
    }

    //通过商品类型查询商品
    //eg: /search/type=TV  查询TV
    @RequestMapping(value = "/search/type",method = RequestMethod.GET)
    public String searchByType(Model model, HttpServletRequest str){
        List<Product> products = productService.finAllByType(str.getParameter("producttype"));
        boolean pro_isnull = false;
        if(products == null || products.isEmpty())  pro_isnull=true;//如果为空设为true
        model.addAttribute("products",products);
        model.addAttribute("pro_isnull", pro_isnull);
        return  "/Product/search";
    }

}
