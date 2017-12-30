package com.project_management.shoppingweb.controller.Seller.ModifySellerInformation;


import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.service.Seller.Seller_CopyFile;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/Seller/ModifySellerInformation")
public class ModifySellerInformationController {

    @Autowired
    private Seller_SellerService sellerSellerService;


    private  long sellerID = -1;


    //进入修改商家个人信息前
    @RequestMapping("ModifySellerInformationHandler")
    public String jumpToModifySellerInformationPage(@ModelAttribute("SellerID")long sellerId,
                                                    Model model,RedirectAttributes attributes)
    {
        sellerID = sellerId;
        Seller seller = sellerSellerService.getSellerById(sellerID);
        if(seller == null)
        {
            attributes.addAttribute("errorMessage","sellerId is wrong!");
            return "redirect:/error/errorHandler";
        }

        model.addAttribute("Sculpture",seller.getSculpture());
        model.addAttribute("Address",seller.getAddress());
        model.addAttribute("Email",seller.getEmail());
        model.addAttribute("Passwd",seller.getPassword());
        model.addAttribute("PhoneNum",seller.getPhoneNum());
        model.addAttribute("Username",seller.getUsername());
        model.addAttribute("Sculpture",seller.getSculpture());
        model.addAttribute("Shopname",seller.getShopname());
        model.addAttribute("Catogery",seller.getCatogery());
        model.addAttribute("SellerID",sellerID);
        return "/Seller/ModifySellerInformationPage";
    }

    //修改后的处理controller
    @RequestMapping("ModifySellerInformation")
    public String modifyFiveItem(@RequestParam(value = "SellerID",required = true)long userId,
                                 @RequestParam(value = "Username",required = true)String userName,
                                 @RequestParam(value = "Email",required = true)String email,
                                 @RequestParam(value = "Passwd",required = true)String passwd,
                                 @RequestParam(value = "PhoneNum",required = true)String phoneNum,
                                 @RequestParam(value = "Address",required = true)String address,
                                 @RequestParam(value = "Sculpture",required = false)MultipartFile file,
                                 @RequestParam(value = "Shopname",required = true)String shopname,
                                 @RequestParam(value = "Catogery")String catogery,
                                 RedirectAttributes attributes

    ){

        Seller newSeller = sellerSellerService.getSellerById(sellerID);
        newSeller.setAddress(address);
        newSeller.setEmail(email);
        newSeller.setPassword(passwd);
        newSeller.setPhoneNum(phoneNum);
        if(file != null && (! "".equals(file.getOriginalFilename())) )
        {
            System.out.println(file.getOriginalFilename());
            newSeller.setSculpture(Seller_CopyFile.getInstance().copyFile(file));
        }

        newSeller.setUsername(userName);
        newSeller.setShopname(shopname);
        newSeller.setCatogery(catogery);
        attributes.addAttribute("SellerID",sellerID);

        //判断是否写入正确用户名、店铺名、分类（1.用户名字、店铺名字长度2-19 2.分类必须匹配首页分类名）
        if(newSeller.getUsername().length()<2||newSeller.getUsername().length()>=20)
        {
            attributes.addAttribute("errorMessage","The length of sellerName must be more than 1 word " +
                    "and less than 20 words !");
            return "redirect:/error/errorHandler";
        }
        if(newSeller.getShopname().length()<2||newSeller.getShopname().length()>=20)
        {
            attributes.addAttribute("errorMessage","The length of sellerShopName must be more than 1 word " +
                    "and less than 20 words !");
            return "redirect:/error/errorHandler";
        }
        if(!(newSeller.getCatogery().equals("TV & Home Theater") ||
                newSeller.getCatogery().equals("Computers & Tablets")  ||
                newSeller.getCatogery().equals("Cell Phones") ||
                newSeller.getCatogery().equals("Cameras & Camcorders")||
                newSeller.getCatogery().equals("Audio") ||
                newSeller.getCatogery().equals("Car Electronics & GPS") ||
                newSeller.getCatogery().equals("Video, Games, Movies & Music") ||
                newSeller.getCatogery().equals("Health, Fitness & Sports") ||
                newSeller.getCatogery().equals("Home & Office")))
        {
            attributes.addAttribute("errorMessage","Your SellerCatogery is not allowed!" +
                    "Please correct yourCatogery ");
            return "redirect:/error/errorHandler";
        }

        //判断邮箱格式:
        /**
         * 1.首位为字母或下划线
         * 2.第二位到@之前为字母数字组合
         * 3.@
         * 4.@之后字母数字组合至少一位
         * 5.小数点.
         * 6.最后为至少一位的字母组合
         *
         * 7.qq邮箱9-11位
         */
        Pattern p1,qq;
        //p1= Pattern.compile("[a-zA-Z_]{1,}[0-9a-zA-Z_]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,}[a-zA-z]{1,}");
        p1= Pattern.compile("[a-zA-Z_]{1,}[0-9a-zA-Z_]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,}com");
        qq= Pattern.compile("[0-9]{9,11}@qq\\.com");
        //p1= Pattern.compile(".*@*.com.*");
        Matcher m1 = p1.matcher(email);
        Matcher m2 = qq.matcher(email);
        boolean b1 = m1.matches();
        boolean b2 = m2.matches();
        if(!(b1||b2))
        {
            attributes.addAttribute("errorMessage","The format of email is illegal " );
            return "redirect:/error/errorHandler";
        }

        //判断手机号格式：
        /**
         * 十一位数字 开头为13-18
         */
        Pattern p3;
        p3= Pattern.compile("1[3-8][0-9]{9}");
        Matcher m3 = p3.matcher(phoneNum);
        boolean b3 = m3.matches();
        if(!b3)
        {
            attributes.addAttribute("errorMessage","The format of phoneNum is illegal " );
            return "redirect:/error/errorHandler";
        }



        //是否写入
        if(sellerSellerService.writeInInformation(newSeller))
            return "redirect:/Seller/Main";
        else
        {
            attributes.addAttribute("errorMassage","modify seller information fail");
            return "redirect:/error/errorHandler";
        }
    }




    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }


}
