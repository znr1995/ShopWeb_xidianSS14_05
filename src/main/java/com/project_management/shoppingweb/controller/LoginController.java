package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.service.User.User_LoginService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private User_LoginService userLoginService;

    @RequestMapping(value = "/")
    public String main(){

        return "redirect:/homepage";

    }



    //商家登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest httpServletRequest, RedirectAttributes attributes) {
        String account = httpServletRequest.getParameter("account_login");
        String password = httpServletRequest.getParameter("password_login");
        String pattern = ".*@*.com.*";
        long id;


        //有bug,用户名和邮箱一样呢

        if (Pattern.matches(pattern, account)) {
            //以邮箱方式登录
            id = userLoginService.sellerLoginByEmail(account,password);
        } else {
            //用户名方式登录
            id = userLoginService.sellerLoginByUsername(account, password);
        }

        if(id < 0)
        {
            attributes.addAttribute("errorMessage",getErrorMessage(id));
           return "redirect:/error/errorHandler";
        }

        attributes.addAttribute("SellerID",id);
        return "redirect:/Seller/Main";
    }


    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.GET,params = "action=UserLogin")
    public String userLogin(HttpServletRequest httpServletRequest, RedirectAttributes attributes) {
        String account = httpServletRequest.getParameter("account_login");
        String password = httpServletRequest.getParameter("password_login");
        String pattern = ".*@*.com.*";
        long id;
        //TODO:有bug,用户名和邮箱一样呢

        //以邮箱方式登录
        if (Pattern.matches(pattern, account)) {
            id = userLoginService.userLoginByEmail(account,password);
        } else {
            //用户名方式登录
            id = userLoginService.userLoginByUsername(account, password);
        }

        if(id < 0)
        {
            attributes.addAttribute("errorMessage",getErrorMessage(id));
            return "redirect:/error/errorHandler";
        }

        attributes.addAttribute("UserID",id);
        return "redirect:/User/Main";
    }

    String getErrorMessage(long id)
    {
        switch ((int)id)
        {
            case -1:
                return  "email or username not exist!";
            case -2:
                return  "password is wrong!";
            default:
                return "there are something wrong!";
        }
    }
}



