package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_TradeService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FeedBackRemarksController {
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/feedbackRemarks", method = RequestMethod.POST)

    public String FeedBackRemarks(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String TradeID = request.getParameter("TradeID");
        User user = userService.findByUserId(Long.parseLong(UserID));

        List<Trade> TradeList = new ArrayList<Trade>();
        TradeList = tradeService.findByTradeId(Long.parseLong(TradeID));
        if(TradeList.size() != 0){
            String Remark = TradeList.get(0).getFeedbackRemarks();
            model.addAttribute("UserName",user.getUsername());
            model.addAttribute("Remark", Remark);
        }
        model.addAttribute("UserName",user.getUsername());
        model.addAttribute("UserID", UserID);
        model.addAttribute("TradeID", TradeID);
        return "/User/FeedBackRemarksNew";
    }
}
