package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.service.Seller.SellerSQLFunction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * Created by sightmaple on 2017/12/10.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/")
    public String main(){

        return "/Login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(HttpServletRequest httpServletRequest, RedirectAttributes attributes) {
        String account = httpServletRequest.getParameter("account_login");
        String password = httpServletRequest.getParameter("password_login");
        String pattern = ".*@*.com.*";
        int id;
        //TODO:有bug,用户名和邮箱一样呢
        //以邮箱方式登录
        if (Pattern.matches(pattern, account)) {
            id = SellerSQLFunction.getInstance().loginByEmail(account, password);
        } else {
            //用户名方式登录
            id = SellerSQLFunction.getInstance().loginByUsername(account, password);
        }

        if (id <= 0) {
            //error
            attributes.addAttribute("errorMessage", "no such account");
            return "redirect:/error/errorHandler";
        } else if (id >= 10000 && id < 20000) {
            //user id
            attributes.addAttribute("UserID",id);
            return "redirect:/User/Main";
        } else if (id >= 20000 && id < 30000) {
            //seller id
            attributes.addAttribute("SellerID",id);
            return "redirect:/Seller/Main";
        } else {
            //admin id
            attributes.addAttribute("AdminID", id);
            return "redirect:/Admin/Main";
        }
    }
}
