package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import com.project_management.shoppingweb.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class AddIntoShoppingCartController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/AddIntoShoppingCart", method = RequestMethod.POST)

    public String AddIntoShoppingCart(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ProductID = request.getParameter("ProductID");
        String ShopID = request.getParameter("ShopID");
        Product product = productService.findProductByProductID(Long.parseLong(ProductID));
        String ProductName = product.getProductName();

        if(UserID.equals("-1")){
//            model.addAttribute("UserID", UserID);
//            model.addAttribute("ProductID", ProductID);
//            model.addAttribute("ShopID", ShopID);
//            System.out.println("no");
//            model.addAttribute("ProductName", ProductName);
//            return "/User/productdetial";
            boolean isnull = false;
            if(product == null) isnull = true;
            model.addAttribute("productdetail", product);
            model.addAttribute("proisnull", isnull);
            long UserIDD = Long.parseLong(request.getParameter("UserID"));
            model.addAttribute("UserID", UserID);
            if(UserIDD == -1){
                model.addAttribute("UserName", "UserName");
            }
            else{
                User user = userService.findByUserId(UserIDD);
                model.addAttribute("UserName", user.getUsername());
            }
            return "/User/ProductDetail";
        }

        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = shoppingCartService.findAllByUserId(Long.parseLong(UserID));
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            if(GlobalShoppingCart.get(i).getProductId() == Long.parseLong(ProductID)){
//                model.addAttribute("UserID", UserID);
//                model.addAttribute("ProductID", ProductID);
//                model.addAttribute("ShopID", ShopID);
//                System.out.println("no");
//                model.addAttribute("ProductName", ProductName);
//                return "/User/productdetial";
                boolean isnull = false;
                if(product == null) isnull = true;
                model.addAttribute("productdetail", product);
                model.addAttribute("proisnull", isnull);
                long UserIDD = Long.parseLong(request.getParameter("UserID"));
                model.addAttribute("UserID", UserID);
                if(UserIDD == -1){
                    model.addAttribute("UserName", "UserName");
                }
                else{
                    User user = userService.findByUserId(UserIDD);
                    model.addAttribute("UserName", user.getUsername());
                }
                return "/User/ProductDetail";
            }
        }

        String ProductAmount = request.getParameter("ProductAmount");
        if(ProductAmount.equals("")){
//            model.addAttribute("UserID", UserID);
//            model.addAttribute("ProductID", ProductID);
//            model.addAttribute("ShopID", ShopID);
//            System.out.println("no");
//            model.addAttribute("ProductName", ProductName);
//            return "/User/productdetial";
            boolean isnull = false;
            if(product == null) isnull = true;
            model.addAttribute("productdetail", product);
            model.addAttribute("proisnull", isnull);
            long UserIDD = Long.parseLong(request.getParameter("UserID"));
            model.addAttribute("UserID", UserID);
            if(UserIDD == -1){
                model.addAttribute("UserName", "UserName");
            }
            else{
                User user = userService.findByUserId(UserIDD);
                model.addAttribute("UserName", user.getUsername());
            }
            return "/User/ProductDetail";
        }
        int ProductNumber;
        try{
            ProductNumber = Integer.parseInt(ProductAmount);
        }
        catch (Exception e){
//            model.addAttribute("UserID", UserID);
//            model.addAttribute("ProductID", ProductID);
//            model.addAttribute("ShopID", ShopID);
//            System.out.println("no");
//            model.addAttribute("ProductName", ProductName);
//            return "/User/productdetial";
            boolean isnull = false;
            if(product == null) isnull = true;
            model.addAttribute("productdetail", product);
            model.addAttribute("proisnull", isnull);
            long UserIDD = Long.parseLong(request.getParameter("UserID"));
            model.addAttribute("UserID", UserID);
            if(UserIDD == -1){
                model.addAttribute("UserName", "UserName");
            }
            else{
                User user = userService.findByUserId(UserIDD);
                model.addAttribute("UserName", user.getUsername());
            }
            return "/User/ProductDetail";
        }

        if(ProductNumber <= 0){
//            model.addAttribute("UserID", UserID);
//            model.addAttribute("ProductID", ProductID);
//            model.addAttribute("ShopID", ShopID);
//            System.out.println("no");
//            model.addAttribute("ProductName", ProductName);
//            return "/User/productdetial";
            boolean isnull = false;
            if(product == null) isnull = true;
            model.addAttribute("productdetail", product);
            model.addAttribute("proisnull", isnull);
            long UserIDD = Long.parseLong(request.getParameter("UserID"));
            model.addAttribute("UserID", UserID);
            if(UserIDD == -1){
                model.addAttribute("UserName", "UserName");
            }
            else{
                User user = userService.findByUserId(UserIDD);
                model.addAttribute("UserName", user.getUsername());
            }
            return "/User/ProductDetail";
        }


        Date now = new Date();
        ShoppingCart shoppingCart = new ShoppingCart();
        //shoppingCart.setShoppingcartId((long)1);
        shoppingCart.setCreatetime(now);
        shoppingCart.setProductAmount(ProductNumber);
        shoppingCart.setProductId(Long.parseLong(ProductID));
        shoppingCart.setUserId(Long.parseLong(UserID));

       shoppingCartService.save(shoppingCart);

       System.out.println("ok");
//        model.addAttribute("UserID", UserID);
//        model.addAttribute("ProductID", ProductID);
//        model.addAttribute("ShopID", ShopID);
//        model.addAttribute("ProductName", ProductName);
//        return "/User/productdetial";
        boolean isnull = false;
        if(product == null) isnull = true;
        model.addAttribute("productdetail", product);
        model.addAttribute("proisnull", isnull);
        long UserIDD = Long.parseLong(request.getParameter("UserID"));
        model.addAttribute("UserID", UserID);
        if(UserIDD == -1){
            model.addAttribute("UserName", "UserName");
        }
        else{
            User user = userService.findByUserId(UserIDD);
            model.addAttribute("UserName", user.getUsername());
        }
        return "/User/ProductDetail";

    }
}
