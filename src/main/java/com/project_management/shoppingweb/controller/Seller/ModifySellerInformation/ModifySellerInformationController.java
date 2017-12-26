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
