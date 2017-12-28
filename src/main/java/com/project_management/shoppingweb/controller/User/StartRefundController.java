package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Seller;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StartRefundController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private SellerService sellerService;
    @RequestMapping(value = "/StartRefund", method = RequestMethod.GET)
    public String StartRefund(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String TradeID = request.getParameter("TradeID");

        List<Trade> TradeList = new ArrayList<Trade>();
        TradeList = tradeService.findByTradeId(Long.parseLong(TradeID));
        if(TradeList.size() != 0){
            TradeList.get(0).setTradeStatus(4);
            tradeService.save(TradeList.get(0));
        }

        List<Trade> MyOrder = new ArrayList<Trade>();
        MyOrder = tradeService.findAllByUserId(Long.parseLong(UserID));
        List<Trade> MyOrderS0 = new ArrayList<Trade>();
        List<Trade> MyOrderS12 = new ArrayList<Trade>();
        List<Trade> MyOrderS3 = new ArrayList<Trade>();
        List<Trade> MyOrderS45 = new ArrayList<Trade>();

        if(MyOrder.size() == 0){
            model.addAttribute("UserID", UserID);
            return "/User/MyOrderNew";
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
            if(MyOrder.get(i).getTradeStatus() == 4||MyOrder.get(i).getTradeStatus() == 5){
                MyOrderS45.add(MyOrder.get(i));
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
        if(MyOrderS45.size() != 0){
            List<TradeToShow> tradeToShowsS45 = new ArrayList<TradeToShow>();
            for(int i = 0; i < MyOrderS45.size(); i++){
                TradeToShow tradeToShow = new TradeToShow();
                tradeToShow.trade = MyOrderS45.get(i);
                Seller seller = sellerService.findBySellerId(MyOrderS45.get(i).getSellerId());
                tradeToShow.SellerName = seller.getShopname();
                tradeToShowsS45.add(tradeToShow);
            }
            model.addAttribute("MyOrderS45", tradeToShowsS45);
        }


        model.addAttribute("UserID", UserID);


        return "/User/MyOrderNew";
    }
}
