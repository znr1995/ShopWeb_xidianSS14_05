package com.project_management.shoppingweb.controller.User;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.TradeDetail;
import com.project_management.shoppingweb.domain.User;
import com.project_management.shoppingweb.service.User.User_ProductService;
import com.project_management.shoppingweb.service.User.User_TradeDetailService;
import com.project_management.shoppingweb.service.User.User_TradeService;
import com.project_management.shoppingweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ExtendRemarksController {
    @Autowired
    private UserService userService;
    @Autowired
    private User_ProductService productService;
    @Autowired
    private User_TradeService tradeService;
    @Autowired
    private User_TradeDetailService tradeDetailService;

    @RequestMapping(value = "/ExtendRemarks", method = RequestMethod.POST)
    public String ExtendRemarks(HttpServletRequest request, Model model){
        String UserID = request.getParameter("UserID");
        String ProductID = request.getParameter("ProductID");

        model.addAttribute("UserID", UserID);
        if(UserID.equals("-1")){
            model.addAttribute("UserName", "UserName");
        }
        else{
            User user = userService.findByUserId(Long.parseLong(UserID));
            model.addAttribute("UserName", user.getUsername());
        }


        Product product = productService.findProductByProductID(Long.parseLong(ProductID));
        if(product == null){
            return "/User/productoops";
        }

        boolean isnull = false;
        if(product == null) isnull = true;
        model.addAttribute("productdetail", product);
        model.addAttribute("proisnull", isnull);

        List<TradeDetail> tradeDetails = tradeDetailService.findAllByProductId(Integer.parseInt(ProductID));
        if(tradeDetails.size() == 0){
            return "/User/ProductDetailWithRemark";
        }
        List<RemarkToShow> remarkToShows = new ArrayList<RemarkToShow>();
        for(int i = 0; i < tradeDetails.size(); i++){
            Trade trade = tradeService.findByTradeId(tradeDetails.get(i).getTradeId()).get(0);
            if(trade.getFeedbackRemarks().equals("")){continue;}
            RemarkToShow remarkToShow = new RemarkToShow();
            remarkToShow.Remark = trade.getFeedbackRemarks();
            remarkToShow.Time = trade.getTradeFinishTime();
            remarkToShow.UserName = userService.findByUserId(trade.getUserId()).getUsername();
            remarkToShows.add(remarkToShow);
        }

        model.addAttribute("remarkToShows", remarkToShows);
        return "/User/ProductDetailWithRemark";
    }
}

class RemarkToShow{
    public String UserName;
    public Date Time;
    public String Remark;
}
