package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.Seller.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by sightmaple on 2017/12/10.
 */
@Controller
public class RegisterController {
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes){
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password_register");
        int type = Integer.valueOf(httpServletRequest.getParameter("register_type"));
        SellerSQLFunction sellerSQLFunction = SellerSQLFunction.getInstance();
        boolean result = sellerSQLFunction.registerAccount(email,password,type);
        if (result){

            return "/Login";
        }else {
            attributes.addAttribute("errorMessage","fail to register!");
            return "redirect:/error/errorHandler";
        }
    }
}
