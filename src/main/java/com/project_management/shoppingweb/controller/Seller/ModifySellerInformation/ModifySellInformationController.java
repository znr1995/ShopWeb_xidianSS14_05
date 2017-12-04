package com.project_management.shoppingweb.controller.Seller.ModifySellerInformation;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.service.SellerSQLFunction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.project_management.shoppingweb.service.SellSQLInterface;

@RequestMapping("/modify")
public class ModifySellInformationController {
    SellerSQLFunction sellerFunction = new SellerSQLFunction();
    @RequestMapping(value = "/checkpasswd")                     //密码确认
    public boolean checkpasswd(@RequestParam(value = "Seller",required = true)Seller seller,
                               @RequestParam(value = "passwd",required = true)String passwd){
        if(seller.getPasswd().equals(passwd))
            return true;
        else
            return false;
    }

    //上一个方法返回true则跳转下一个页面(暂缺)

    @RequestMapping(value = "/modifyFiveItem")
    // public String modifyFiveItem(@RequestParam(value = "Seller",required = true)Seller seller)
    public String modifyFiveItem(@RequestParam(value = "userId",required = true)long userId,
                                 @RequestParam(value = "userName",required = true)String userName,
                                 @RequestParam(value = "email",required = true)String email,
                                 @RequestParam(value = "passwd",required = true)String passwd,
                                 @RequestParam(value = "phoneNum",required = true)String phoneNum,
                                 @RequestParam(value = "address",required = true)String address,
                                 @RequestParam(value = "sculpture",required = true)String sculpture

    ){
        Seller newSeller=sellerFunction.getSellerInfromation(userId);    //getSellerInformation和writeInInformation等待数据库实现
        newSeller.setAddress(address);
        newSeller.setEmail(email);
        newSeller.setPasswd(passwd);
        newSeller.setPhoneNum(phoneNum);
        newSeller.setSculpture(sculpture);
        newSeller.setUsername(userName);
        if(sellerFunction.writeInInformation(newSeller))
            return "success";
        else
            return "fail";
    }
}
