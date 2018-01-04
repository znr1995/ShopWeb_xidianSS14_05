package com.project_management.shoppingweb.controller;


import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import com.project_management.shoppingweb.service.User.User_RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


/**
 * Created by sightmaple on 2017/12/10.
 * 错误测试,可以提醒用户名和邮箱是否重名
 */
@Controller
public class RegisterController {

    @Autowired
    private User_RegisterService userRegisterService;

    @Autowired
    private Seller_SellerService seller_sellerService;

    private String emailString;

    //默认头像
    private String DEFATE_SCULPTURE = "/head.jpg";

    @RequestMapping(value = "/Seller/Register")
    public String beforeRegister(Model model, RedirectAttributes attributes)
    {
        if(!seller_sellerService.hasCorrentPrice())
        {
            attributes.addAttribute("error","the shop price has not know,the admin not add it.try register later");
            return  "redirect:/error/errorHandler";
        }

        model.addAttribute("shopPrice",seller_sellerService.getShopPrice());
        return "/Seller/register";
    }

    @RequestMapping(value = "sellerRegister",method = RequestMethod.POST)
    public String sellerRegister(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes){
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password_register");
        String shopName = httpServletRequest.getParameter("Shopname");
        String catogery = httpServletRequest.getParameter("catogery");

        if(userRegisterService.sellerVaildEmail(email) && userRegisterService.sellerVaildUsername(username))
        {
            Seller seller = new Seller();
            seller.setCatogery(catogery);
            seller.setShopname(shopName);
            seller.setPassword(password);
            seller.setPhoneNum(phone);
            seller.setEmail(email);
            seller.setUsername(username);
            seller.setSculpture(DEFATE_SCULPTURE);
            seller.setApplyState(2);// 1 - 通过， 2 - 未通过, 3-拉黑

            //判断商家注册信息格式是否规范（用户名、邮箱名是否重复已判断）
            /**
             * 1.用户名、店铺名长度2-19
             * 2.邮箱格式
             * 3.手机格式
             * 4.密码非空
             */
            //判断用户名长度
            if(seller.getUsername().length()<2||seller.getUsername().length()>=20)
            {
                attributes.addAttribute("errorMessage","The length of sellerName must be more than 1 word " +
                        "and less than 20 words !");
                return "redirect:/error/errorHandler";
            }

            //判断手机号格式：
            /**
             * 十一位数字 开头为13-18
             */
            Pattern p3;
            p3= Pattern.compile("1[3-8][0-9]{9}");
            Matcher m3 = p3.matcher(phone);
            boolean b3 = m3.matches();
            if(!b3)
            {
                attributes.addAttribute("errorMessage","The format of phoneNum is illegal.Your phonenum must " +
                        "start with 13-18 and the length of your phonenum must be 11." );
                return "redirect:/error/errorHandler";
            }

            //判断邮箱格式:
            /**
             * 1.首位为字母或下划线
             * 2.第二位到@之前为字母数字组合
             * 3.@
             * 4.@之后字母数字组合至少一位
             * 5.小数点.
             * 6.最后为至少一位的字母组合(1-6已不用)
             * 正则表达式^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$
             * 7.qq邮箱9-11位(首位非0)
             */
            Pattern p1,qq;
            p1= Pattern.compile("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
            //p1= Pattern.compile("[a-zA-Z_]{1,}[0-9a-zA-Z_]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,}com");
            qq= Pattern.compile("[0-9]{9,11}@qq\\.com");
            //p1= Pattern.compile(".*@*.com.*");
            Matcher m1 = p1.matcher(email);
            Matcher m2 = qq.matcher(email);
            boolean b1 = m1.matches();
            boolean b2 = m2.matches();
            if(!(b1||b2))
            {
                attributes.addAttribute("errorMessage","The format of email is illegal.Please use QQemail or" +
                        " others.For example, '353171537@qq.com' or 'gsy@163.com'" +
                        "测试版正则表达式（待定）：" +
                        "^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$" );
                return "redirect:/error/errorHandler";
            }

            //判断密码格式
            if(seller.getPassword().equals(""))
            {
                attributes.addAttribute("errorMessage","The password can't be null");
                return "redirect:/error/errorHandler";
            }

            //判断店名长度
            if(seller.getShopname().length()<2||seller.getShopname().length()>=20)
            {
                attributes.addAttribute("errorMessage","The length of sellerShopName must be more than 1 word " +
                        "and less than 20 words !");
                return "redirect:/error/errorHandler";
            }

            userRegisterService.sellerRegister(seller);
            return "redirect:/Seller/login";
        }
        else
        {
            attributes.addAttribute("errorMessage","email or username has been rigister!");
            return "redirect:/error/errorHandler";
        }
    }


    @RequestMapping(value = "userRegister",method = RequestMethod.POST)
    public String userRegister(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes){
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password_register");
        if (userRegisterService.userVaildEmail(email) && userRegisterService.userVaildUsername(username)){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setTel(phone);
            user.setUsername(username);
            user.setCreatetime(new Date());
            user.setSex(1);  // 1 - 男， 2 - 女
            user.setState(1); // 1 - 可用 ，2 - 黑名单
            userRegisterService.userRegister(user);
            return "/User/loginNew";
        }else {
            attributes.addAttribute("errorMessage","fail to register!");
            return "redirect:/error/errorHandler";
        }
    }


}
