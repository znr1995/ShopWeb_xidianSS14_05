package com.project_management.shoppingweb.controller;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Properties;
import java.util.Random;


/**
 * Created by sightmaple on 2017/12/10.
 * 错误测试,可以提醒用户名和邮箱是否重名
 */
@Controller
public class RegisterController {

    @Autowired
    private User_RegisterService userRegisterService;

    private String emailString;

    //默认头像
    private String DEFATE_SCULPTURE = "/default.png";

    @RequestMapping(value = "/register",method = RequestMethod.GET, params = "action=getNum")
    public String emailCheck(HttpServletRequest httpServletRequest, Model model)
    {
        String num = generateRandomNum();
        sendStr(num,httpServletRequest.getParameter("email"));
        model.addAttribute("num",num);
        model.addAttribute("username",httpServletRequest.getParameter("username"));
        model.addAttribute("email",httpServletRequest.getParameter("email"));
        model.addAttribute("phone_number",httpServletRequest.getParameter("phone_number"));
        model.addAttribute("password_register",httpServletRequest.getParameter("password_register"));
        model.addAttribute("Shopname",httpServletRequest.getParameter("Shopname"));
        model.addAttribute("Catogery",httpServletRequest.getParameter("Catogery"));

        return "/Login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET, params = "action=SellerSignup")
    public String sellerRegister(HttpServletRequest httpServletRequest, Model model, RedirectAttributes attributes){
        String username = httpServletRequest.getParameter("username");
        String email = httpServletRequest.getParameter("email");
        String phone = httpServletRequest.getParameter("phone_number");
        String password = httpServletRequest.getParameter("password_register");
        String shopName = httpServletRequest.getParameter("Shopname");
        String catogery = httpServletRequest.getParameter("Catogery");

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
            userRegisterService.sellerRegister(seller);
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
        if (userRegisterService.userVaildEmail(email) && userRegisterService.userVaildUsername(username)){
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setTel(phone);
            user.setUsername(username);
            userRegisterService.userRegister(user);
            return "/Login";
        }else {
            attributes.addAttribute("errorMessage","fail to register!");
            return "redirect:/error/errorHandler";
        }
    }


    private String generateRandomNum()
    {
        String str = "";
        Random r = new Random(10);
        for(int i=0;i<6;i++)
        {
            str += String.valueOf(r.nextInt());
        }
        return str;
    }

    private boolean sendStr(String text,String to)
    {
        //创建连接对象 连接到邮件服务器
        Properties properties = new Properties();
        //设置发送邮件的基本参数
        //发送邮件服务器
        properties.put("mail.smtp.host", "smtp.163.com");
        //发送端口
        properties.put("mail.smtp.port", "25");
        properties.put("userName","13772077200@163.com"); //这里填上你的邮箱（发送方）
        properties.put("mail.smtp.auth", "true");
        //设置发送邮件的账号和密码
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //两个参数分别是发送邮件的账户和密码
                return new PasswordAuthentication("13772077200@163.com","sqm123456");
            }
        });

       try {
           //创建邮件对象
           Message message = new MimeMessage(session);
           //设置发件人
           message.setFrom(new InternetAddress("13772077200@163.com"));
           //设置收件人
           message.setRecipient(Message.RecipientType.TO,new InternetAddress(to));
           //设置主题
           message.setSubject("email Autothentication");

           message.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(properties.getProperty("userName")));
           message.setSentDate(new Date());
           //设置邮件正文  第二个参数是邮件发送的类型
           message.setContent("验证信息为如下字符串:" + text+"/r/rn请在3分钟内完成验证","text/html;charset=UTF-8");
           //发送一封邮件
           Transport.send(message);
       }
       catch (Exception e)
       {
           System.out.println(e.getMessage());
           return false;
       }
    return true;

    }
}
