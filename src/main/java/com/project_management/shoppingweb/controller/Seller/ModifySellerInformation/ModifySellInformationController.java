package com.project_management.shoppingweb.controller.Seller.ModifySellerInformation;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.service.SellerSQLFunction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project_management.shoppingweb.service.SellSQLInterface;


@RequestMapping("/Seller/ModifySellerInformation")
public class ModifySellInformationController {
    private  long sellerID = -1;
    SellerSQLFunction sellerFunction = new SellerSQLFunction();

    @RequestMapping("ModifySellerInformationHandler")
    public String jumpToModiySellerInformationPage(@ModelAttribute("SellerID") long sellerId, Model model)
    {
        sellerID = sellerId;
        Seller seller = sellerFunction.getSellerInfromation(sellerID);
        model.addAttribute("Sculpture",seller.getSculpture());
        model.addAttribute("Address",seller.getAddress());
        model.addAttribute("Email",seller.getEmail());
        model.addAttribute("Passwd",seller.getPasswd());
        model.addAttribute("PhoneNum",seller.getPhoneNum());
        model.addAttribute("Username",seller.getUsername());
        return "Seller/ModifySellerInformationPage";
    }



    @RequestMapping("ModifySellerInfromation")
    // public String modifyFiveItem(@RequestParam(value = "Seller",required = true)Seller seller)
    public String modifyFiveItem(@RequestParam(value = "userId",required = true)long userId,
                                 @RequestParam(value = "userName",required = true)String userName,
                                 @RequestParam(value = "email",required = true)String email,
                                 @RequestParam(value = "passwd",required = true)String passwd,
                                 @RequestParam(value = "phoneNum",required = true)String phoneNum,
                                 @RequestParam(value = "address",required = true)String address,
                                 @RequestParam(value = "sculpture",required = true)String sculpture

    ){

        //TODO:如何告诉界面返回成功
        Seller newSeller=sellerFunction.getSellerInfromation(userId);    //getSellerInformation和writeInInformation等待数据库实现
        newSeller.setAddress(address);
        newSeller.setEmail(email);
        newSeller.setPasswd(passwd);
        newSeller.setPhoneNum(phoneNum);
        newSeller.setSculpture(sculpture);
        newSeller.setUsername(userName);
        if(sellerFunction.writeInInformation(newSeller))
            return "../Seller/Main";
        else
            //TODO:eroor hander
            return "error/false";
    }
}
