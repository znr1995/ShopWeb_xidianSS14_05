package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.SellerService;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_TradeService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
public class SearchOrderController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private SellerService sellerService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/SearchOrder", method = RequestMethod.POST)
    public String SearchOrder(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        User user = userService.findByUserId(Long.parseLong(UserID));
        model.addAttribute("UserID", UserID);
        model.addAttribute("UserName", user.getUsername());

        String StartDate = request.getParameter("StartDate");
        String EndDate = request.getParameter("EndDate");

        if(StartDate.equals("") == true && EndDate.equals("") == true){
            return "/User/SB";
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(StartDate.equals("") == false && EndDate.equals("") == false){
            try{
                Date SD = df.parse(StartDate);
                Date ED = df.parse(EndDate);
                if(SD.getTime() > ED.getTime()){
                    return "/User/SB";
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        List<Trade> trades = new ArrayList<Trade>();
        trades = tradeService.findAllByUserId(Long.parseLong(UserID));
        if(trades.size() == 0){
            return "/User/MyOrderNewWithHistory";
        }

        List<TradeToShow> tradeToShows = new ArrayList<TradeToShow>();
        for(int i = 0; i < trades.size(); i++){
            if(trades.get(i).getTradeStatus() != 3){
                continue;
            }
            TradeToShow tradeToShow = new TradeToShow();
            tradeToShow.trade = trades.get(i);
            tradeToShow.SellerName = sellerService.findBySellerId(trades.get(i).getSellerId()).getShopname();
            tradeToShows.add(tradeToShow);
        }

        List<TradeToShow> tradeToShows1 = new ArrayList<TradeToShow>();

        if(StartDate.equals("") == false && EndDate.equals("") == false){
            try{
                Date SD = df.parse(StartDate);
                Date ED = df.parse(EndDate);
                for(int i = 0; i < tradeToShows.size(); i++){
                    if(tradeToShows.get(i).trade.getTradeFinishTime().getTime() <= ED.getTime()&&tradeToShows.get(i).trade.getTradeFinishTime().getTime() >= SD.getTime()){
                        tradeToShows1.add(tradeToShows.get(i));
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else if (StartDate.equals("") == true){
            try{
                Date ED = df.parse(EndDate);
                for(int i = 0; i < tradeToShows.size(); i++){
                    if(tradeToShows.get(i).trade.getTradeFinishTime().getTime() <= ED.getTime()){
                        tradeToShows1.add(tradeToShows.get(i));
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        else {
            try{
                Date SD = df.parse(StartDate);
                for(int i = 0; i < tradeToShows.size(); i++){
                    if(tradeToShows.get(i).trade.getTradeFinishTime().getTime() >= SD.getTime()){
                        tradeToShows1.add(tradeToShows.get(i));
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        model.addAttribute("tradeToShows", tradeToShows1);
        return "/User/MyOrderNewWithHistory";
    }
}
