package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.Address;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.TradeDetail;
import com.project_management.shoppingweb.service.AddressService;
import com.project_management.shoppingweb.service.User.User_TradeDetailService;
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
    @Autowired
    private User_TradeDetailService tradeDetailService;


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
        String SellerName = request.getParameter("SellerName");
        String ProductName = request.getParameter("ProductName");
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
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
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
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
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
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
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
            model.addAttribute("SellerName", SellerName);
            model.addAttribute("ProductName", ProductName);
            if(AddressList.size() == 0)
                return "/User/Pay";
            model.addAttribute("AddressList",AddressList);
            return "/User/Pay";
        }


        Trade newTrade = new Trade();
        newTrade.setAddressId(String.valueOf(AddressList.get(Integer.parseInt(address) - 1).getAddressId()));
        newTrade.setFeedbackRemarks("");
        Date now = new Date();
        newTrade.setTradeCreateTime(now);
        newTrade.setTradeStatus(0);
        newTrade.setTradeTotalMoney(Double.parseDouble(Total));
        newTrade.setUserId(Long.parseLong(UserID));
        newTrade.setSellerId(Long.parseLong(ShopID));
        newTrade.setTradePayWay(PayWay);


        TradeDetail newTradeDetail = new TradeDetail();
        newTradeDetail.setTradeId(tradeService.save(newTrade).getTradeId());
        newTradeDetail.setProductAmount(Integer.parseInt(ProductAmount));
        newTradeDetail.setProductId(Integer.parseInt(ProductID));
        newTradeDetail.setProductTradePrice(Double.parseDouble(Total));

        tradeDetailService.save(newTradeDetail);

        model.addAttribute("UserID", UserID);
        return "/User/Success";
    }
}
