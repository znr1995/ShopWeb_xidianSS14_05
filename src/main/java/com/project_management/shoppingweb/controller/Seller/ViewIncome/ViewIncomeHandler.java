package com.project_management.shoppingweb.controller.Seller.ViewIncome;
import com.project_management.shoppingweb.controller.Seller.ViewTransaction.ViewTransactionController;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.TradeDetail;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/Seller/ViewIncome")
public class ViewIncomeHandler {

    @Autowired
    private Seller_SellerService seller_sellerService;

    private long sellerID = -1;

    class PageDetail{
        private long trandId;
        private double totlePrice;
        private String status;
        private String userName;

        public long getTrandId() {
            return trandId;
        }

        public void setTrandId(long trandId) {
            this.trandId = trandId;
        }

        public double getTotlePrice() {
            return totlePrice;
        }

        public void setTotlePrice(double totlePrice) {
            this.totlePrice = totlePrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String  status) {
            this.status = status;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }




    }

    class PageTradeDetail{
        private String productName;
        private int amount;
        private double productPrice;
        private double totalPrice;

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(double productPrice) {
            this.productPrice = productPrice;
        }

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }

    @RequestMapping("ViewIncomeHandler")
    public String jumpToViewTransaction(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        List<Trade> trades = seller_sellerService.getTradeList(sellerId,0);
        trades.addAll(seller_sellerService.getTradeList(sellerId,3));

        model.addAttribute("totlePrice",seller_sellerService.getTradeSum(trades));
        LinkedList<PageDetail> details = new LinkedList<PageDetail>();
        for(Trade trade : trades)
        {
            PageDetail pageDetail = new PageDetail();
            pageDetail.setTotlePrice(trade.getTradeTotalMoney());
            pageDetail.setTrandId(trade.getTradeId());
            pageDetail.setStatus(getTradeStatus(trade.getTradeStatus()));
            pageDetail.setUserName(seller_sellerService.getUser(trade.getUserId()).getUsername());
            details.add(pageDetail);
        }
        model.addAttribute("details",details);
        model.addAttribute("SellerID",sellerID);
        return "/Seller/IncomeManagement";
    }

    //搜索
    @RequestMapping("Search")
    public String getTradesByDate(@RequestParam("StartDate")String startDate,
                                  @RequestParam("EndDate")String endDate,
                                  HttpServletRequest request,
                                  Model model,
                                  RedirectAttributes attributes)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start= new Date();
        Date end = new Date();
        try {
            start = sdf.parse(startDate);
        }catch (Exception e)
        {
            start = null;
        }
        try {
            end = sdf.parse(endDate);
        }catch (Exception e)
        {
            end = null;
        }

        long sellerId = sellerID;
        try {
            sellerId = Long.valueOf(request.getParameter("SellerID"));
        }catch (Exception e) { }

        if(sellerId == -1)
        {
            attributes.addAttribute("errorMessage","seller id not vail ,please try login to sovle this problem");
            return "redirect:/error/errorHandler";
        }

        List<Trade> trades = seller_sellerService.getTradeListByFindedTime(sellerId,start,end);
        model.addAttribute("totlePrice",seller_sellerService.getTradeSum(trades));
        LinkedList<PageDetail> details = new LinkedList<PageDetail>();
        for(Trade trade : trades) {
            PageDetail pageDetail = new PageDetail();
            pageDetail.setTotlePrice(trade.getTradeTotalMoney());
            pageDetail.setTrandId(trade.getTradeId());
            pageDetail.setStatus(getTradeStatus(trade.getTradeStatus()));
            pageDetail.setUserName(seller_sellerService.getUser(trade.getUserId()).getUsername());
            details.add(pageDetail);
        }
        model.addAttribute("details",details);
        model.addAttribute("SellerID",sellerID);
        return "/Seller/IncomeManagement";
    }
    private String getTradeStatus(int id)
    {
        switch (id)
        {
            case 0:
                return "untreated trade";
            case 1:
                return "wait for deliver Trade";
            case 2:
                return "wait for receive Trade";
            case 3:
                return "Finished Trade";
            case 4:
                return "GoodsRejected Trade";
            case 5:
                return "Return Goods Finished Trade";
            case 6:
                return "Appeal Trade";
        }
        return "unknow status";
    }


    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }

}
