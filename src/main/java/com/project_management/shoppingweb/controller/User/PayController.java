package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.Address;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.service.AddressService;
import com.project_management.shoppingweb.service.User.User_TradeService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PayController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private AddressService addressService;
    @RequestMapping(value = "/Pay",method = RequestMethod.GET)
    public String Pay(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ShopID = request.getParameter("ShopID");
        String ProductID = request.getParameter("ProductID");
        String UnitPrice = request.getParameter("UnitPrice");
        String ProductAmount = request.getParameter("ProductAmount");
        String Total = request.getParameter("Total");
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String PayWay = request.getParameter("PayWay");
        List<Address> AddressList = new ArrayList<Address>();
        AddressList = addressService.findAllByUserId(Long.parseLong(UserID));
        int number = AddressList.size();

        if(account.equals("") || password.equals("") || address.equals("")){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            if(AddressList.size() == 0)
                return "/User/Pay";
            model.addAttribute("AddressList",AddressList);
            return "/User/Pay";
        }

        try {
            int a = Integer.parseInt(address);
        }
        catch (Exception e){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            if(AddressList.size() == 0)
                return "/User/Pay";
            model.addAttribute("AddressList",AddressList);
            return "/User/Pay";
        }

        int b = Integer.parseInt(address);

        if(b > number || Integer.parseInt(address)<= 0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            if(AddressList.size() == 0)
                return "/User/Pay";
            model.addAttribute("AddressList",AddressList);
            return "/User/Pay";
        }

        if(number == 0){
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductAmount", ProductAmount);
            model.addAttribute("Total", Total);
            model.addAttribute("account", account);
            model.addAttribute("password", password);
            if(AddressList.size() == 0)
                return "/User/Pay";
            model.addAttribute("AddressList",AddressList);
            return "/User/Pay";
        }


        Trade newTrade = new Trade();
        //newTrade.setTradeId((long)1);//赋值默认
        newTrade.setAddressId(address);
        newTrade.setFeedbackRemarks("");
        Date now = new Date();
        newTrade.setTradeCreateTime(now);
        newTrade.setTradeFinishTime(now);
        newTrade.setTradeStatus(0);
        newTrade.setTradeTotalMoney(Double.parseDouble(Total));
        newTrade.setUserId(Long.parseLong(UserID));
        newTrade.setSellerId(Long.parseLong(ShopID));
        newTrade.setTradePayWay(PayWay);

        tradeService.save(newTrade);


        model.addAttribute("UserID", UserID);
        return "/User/Success";
    }
}
