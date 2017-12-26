package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.ShoppingCart;
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
public class ModifySCAmountController {
    @Autowired
    private User_ShoppingCartService shoppingCartService;

    @RequestMapping(value = "/ModifyAmount", method = RequestMethod.GET)

    public String ModifySCAmount(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShoppingCartID = request.getParameter("ShoppingCartID");

        List<ShoppingCart> ShoppingCartList = new ArrayList<ShoppingCart>();
        ShoppingCartList = shoppingCartService.findAllByShoppingcartId(Long.parseLong(ShoppingCartID));

        if(ShoppingCartList.size() != 0){
            String Amount = String.valueOf(ShoppingCartList.get(0).getProductAmount());
            model.addAttribute("Amount", Amount);
        }


        model.addAttribute("UserID", UserID);
        model.addAttribute("ShoppingCartID", ShoppingCartID);

        return "/User/ShoppingCartAmount";
    }
}
