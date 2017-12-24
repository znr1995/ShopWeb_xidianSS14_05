package com.project_management.shoppingweb.controller.User;



import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_TradeService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MyOrderController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private SellerService sellerService;
    @RequestMapping(value = "/MyOrder",method = RequestMethod.GET)
    public String MyOrder(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");

        if(UserID.equals("")){
            String ProductID = request.getParameter("ProductID");
            String ShopID = request.getParameter("ShopID");
            String UnitPrice = request.getParameter("UnitPrice");
            Product product = productService.findProductByProductID(Long.parseLong(ProductID));
            Seller seller = sellerService.findBySellerId(Long.parseLong(ShopID));
            String ProductName = product.getProductName();
            String SellerName = seller.getShopname();

            model.addAttribute("ShopID", ShopID);
            model.addAttribute("UserID", UserID);
            model.addAttribute("ProductID", ProductID);
            model.addAttribute("UnitPrice", UnitPrice);
            model.addAttribute("ProductName", ProductName);
            return "/User/productdetial";
        }

        List<Trade> MyOrder = new ArrayList<Trade>();
        MyOrder = tradeService.findAllByUserId(Long.parseLong(UserID));
        List<Trade> MyOrderS0 = new ArrayList<Trade>();
        List<Trade> MyOrderS12 = new ArrayList<Trade>();
        List<Trade> MyOrderS3 = new ArrayList<Trade>();

        if(MyOrder.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/MyOrder";
        }



        for(int i = 0; i < MyOrder.size(); i++){
            if(MyOrder.get(i).getTradeStatus() == 0){
                MyOrderS0.add(MyOrder.get(i));
            }
            if(MyOrder.get(i).getTradeStatus() == 1||MyOrder.get(i).getTradeStatus() == 2){
                MyOrderS12.add(MyOrder.get(i));
            }
            if(MyOrder.get(i).getTradeStatus() == 3){
                MyOrderS3.add(MyOrder.get(i));
            }
        }

        if(MyOrderS0.size() != 0){
            List<TradeToShow> tradeToShowsS0 = new ArrayList<TradeToShow>();
            for(int i = 0; i < MyOrderS0.size(); i++){
                TradeToShow tradeToShow = new TradeToShow();
                tradeToShow.trade = MyOrderS0.get(i);
                Seller seller = sellerService.findBySellerId(MyOrderS0.get(i).getSellerId());
                tradeToShow.SellerName = seller.getShopname();
                tradeToShowsS0.add(tradeToShow);
            }
            model.addAttribute("MyOrderS0", tradeToShowsS0);
        }
        if(MyOrderS12.size() != 0){
            List<TradeToShow> tradeToShowsS12 = new ArrayList<TradeToShow>();
            for(int i = 0; i < MyOrderS12.size(); i++){
                TradeToShow tradeToShow = new TradeToShow();
                tradeToShow.trade = MyOrderS12.get(i);
                Seller seller = sellerService.findBySellerId(MyOrderS12.get(i).getSellerId());
                tradeToShow.SellerName = seller.getShopname();
                tradeToShowsS12.add(tradeToShow);
            }
            model.addAttribute("MyOrderS12", tradeToShowsS12);
        }
        if(MyOrderS3.size() != 0){
            List<TradeToShow> tradeToShowsS3 = new ArrayList<TradeToShow>();
            for(int i = 0; i < MyOrderS3.size(); i++){
                TradeToShow tradeToShow = new TradeToShow();
                tradeToShow.trade = MyOrderS3.get(i);
                Seller seller = sellerService.findBySellerId(MyOrderS3.get(i).getSellerId());
                tradeToShow.SellerName = seller.getShopname();
                tradeToShowsS3.add(tradeToShow);
            }
            model.addAttribute("MyOrderS3", tradeToShowsS3);
        }


        model.addAttribute("UserID", UserID);


        return "/User/MyOrder";
    }
}

class TradeToShow{
    public Trade trade;
    public String SellerName;
}
