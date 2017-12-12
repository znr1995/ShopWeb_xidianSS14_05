package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sightmaple on 2017/12/10.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    private String DEFATE_SCULPTURE = "./default.png";

    @RequestMapping(value = "/register",method = RequestMethod.GET, params = "action=SellerSignup")
    public String sellerRegister(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes){
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password_register");
        String shopName = httpServletRequest.getParameter("Shopname");
        String catogery = httpServletRequest.getParameter("Catogery");

        if(registerService.sellerVaildEmail(email) && registerService.sellerVaildUsername(username))
        {
            Seller seller = new Seller();
            seller.setCatogery(catogery);
            seller.setShopname(shopName);
            seller.setPassword(password);
            seller.setPhoneNum(phone);
            seller.setEmail(email);
            seller.setUsername(username);
            seller.setSculpture(DEFATE_SCULPTURE);
            registerService.sellerRegister(seller);
            return "/Login";
        }
        else
        {
            attributes.addAttribute("errorMessage","email or username has been rigister!");
            return "redirect:/error/errorHandler";
        }
    }


    @RequestMapping(value = "/register",method = RequestMethod.GET, params = "action=UserSignup")
    public String userRegister(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes){
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password_register");
        if (registerService.userVaildEmail(email) && registerService.userVaildUsername(username)){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setTel(phone);
            user.setUsername(username);
            registerService.userRegister(user);
            return "/Login";
        }else {
            attributes.addAttribute("errorMessage","fail to register!");
            return "redirect:/error/errorHandler";
        }
    }
}
