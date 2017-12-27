package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.ShoppingCart;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UploadSCAmountController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;
    @Autowired
    private User_ProductService productService;
    @RequestMapping(value = "/uploadAmount", method = RequestMethod.GET)

    public String UploadSCAmount(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShoppingCartID = request.getParameter("ShoppingCartID");
        String Amount = request.getParameter("Amount");

        List<ShoppingCart> ShoppingCartList = new ArrayList<ShoppingCart>();
        ShoppingCartList = shoppingCartService.findAllByShoppingcartId(Long.parseLong(ShoppingCartID));

        if(ShoppingCartList.size() != 0){
            if(Amount.equals("")){
                model.addAttribute("UserID", UserID);
                model.addAttribute("ShoppingCartID", ShoppingCartID);
                model.addAttribute("Amount", Amount);
                return "/User/ShoppingCartAmountNew";
            }
            int hehe;
            try{
                hehe = Integer.parseInt(Amount);
            }
            catch (Exception e){
                model.addAttribute("UserID", UserID);
                model.addAttribute("ShoppingCartID", ShoppingCartID);
                model.addAttribute("Amount", Amount);
                return "/User/ShoppingCartAmountNew";
            }
            if(hehe <= 0){
                model.addAttribute("UserID", UserID);
                model.addAttribute("ShoppingCartID", ShoppingCartID);
                model.addAttribute("Amount", Amount);
                return "/User/ShoppingCartAmountNew";
            }

            ShoppingCartList.get(0).setProductAmount(hehe);
            shoppingCartService.save(ShoppingCartList.get(0));
        }

        List<ShoppingCart> GlobalShoppingCart = new ArrayList<ShoppingCart>();
        GlobalShoppingCart = shoppingCartService.findAllByUserId(Long.parseLong(UserID));


        if(GlobalShoppingCart.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/ShoppingCartNew";
        }

        List<ShoppingCartToShow> shoppingCartToShowList = new ArrayList<ShoppingCartToShow>();
        for(int i = 0; i < GlobalShoppingCart.size(); i++){
            ShoppingCartToShow shoppingCartToShow = new ShoppingCartToShow();
            shoppingCartToShow.shoppingCart = GlobalShoppingCart.get(i);
            Product product = productService.findProductByProductID(GlobalShoppingCart.get(i).getProductId());
            shoppingCartToShow.Name = product.getProductName();
            shoppingCartToShowList.add(shoppingCartToShow);
        }

        model.addAttribute("UserID", UserID);
        model.addAttribute("GlobalShoppingCart", shoppingCartToShowList);

        return "/User/ShoppingCartNew";
    }
}
