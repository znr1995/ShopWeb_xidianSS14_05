package com.project_management.shoppingweb.controller.User;


import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.service.User.TradeService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MyOrderController {
    @Autowired
    private TradeService tradeService;
    @RequestMapping(value = "/MyOrder",method = RequestMethod.GET)
    public String MyOrder(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");

        if(UserID.equals("")){
            String ProductID = request.getParameter("ProductID");
            String ShopID = request.getParameter("ShopID");
            String UnitPrice = request.getParameter("UnitPrice");

            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("UnitPrice", UnitPrice);
            return "/User/productdetial";
        }

        List<Trade> MyOrder = new ArrayList<Trade>();
        MyOrder = tradeService.findAllByUserId(Long.parseLong(UserID));

        if(MyOrder.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/MyOrder";
        }

//        List<String> SellerList = new ArrayList<String>();
//        List<String> Price = new ArrayList<String>();
//        List<String> PayWay = new ArrayList<String>();
//        List<String> CreateTime = new ArrayList<String>();
//        List<String> Status = new ArrayList<String>();
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//
//        for(int i = 0; i < MyOrder.size(); i++){
//            SellerList.add(String.valueOf(MyOrder.get(i).getSellerId()));
//            Price.add(String.valueOf(MyOrder.get(i).getTradeTotalMoney()));
//            PayWay.add(MyOrder.get(i).getTradePayWay());
//            CreateTime.add(sdf.format(MyOrder.get(i).getTradeCreateTime()));
//            if(MyOrder.get(i).getTradeStatus() == 0)
//                Status.add("Unfinished");
//        }

        model.addAttribute("MyOrder", MyOrder);
        model.addAttribute("UserID", UserID);
//        model.addAttribute("ProductList", SellerList);
//        model.addAttribute("price", Price);
//        model.addAttribute("payway",PayWay);
//        model.addAttribute("createtime", CreateTime);
//        model.addAttribute("status", Status);

        return "/User/MyOrder";
    }
}
