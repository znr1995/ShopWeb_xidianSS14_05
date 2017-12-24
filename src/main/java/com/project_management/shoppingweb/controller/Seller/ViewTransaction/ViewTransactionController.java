package com.project_management.shoppingweb.controller.Seller.ViewTransaction;

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

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/Seller/ViewTransaction")
public class ViewTransactionController {

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

    @RequestMapping("ViewTransactionHandler")
    public String jumpToViewTransaction(@ModelAttribute("SellerID")long sellerId, Model model)
    {
        sellerID = sellerId;
        //0 - 未完成，1 - 完成
        List<Trade> trades = seller_sellerService.getTradeList(sellerId,0);
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
        return "/Seller/ViewTransaction";
    }

    //搜索
    @RequestMapping("Search")
    public String getTradesByDate(@RequestParam("StartDate")String startDate,
                                  @RequestParam("EndDate")String endDate,
                                  @RequestParam("type")int status,
                                  Model model,
                                  RedirectAttributes attributes)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start= new Date();
        Date end = new Date();
        try {
            start = sdf.parse(startDate);
            end = sdf.parse(endDate);
        }catch (Exception e)
        {
            attributes.addAttribute("errorMessage",e.getMessage());
            return "redirect:/error/errorHandler";
        }

        List<Trade> trades = seller_sellerService.getTradeListByTime(sellerID,status,start,end);
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
        return "/Seller/ViewTransaction";
    }

    //查看详细
    @RequestMapping("Detail")
    public String viewDetail(HttpServletRequest request,Model model,RedirectAttributes attributes)
    {
        long tradeId = Long.valueOf(request.getParameter("detail"));
        Trade trade = seller_sellerService.getTrade(tradeId);
        if(trade == null)
        {
            attributes.addAttribute("errorMessage","trade id is wrong!");
            return "redirect:/error/errorHandler";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("userName",seller_sellerService.getSellerById(trade.getSellerId()).getUsername());
        model.addAttribute("tradePayWay",trade.getTradePayWay());
        model.addAttribute("tradeTotalMoney",trade.getTradeTotalMoney());
        model.addAttribute("address",trade.getAddressId());
        model.addAttribute("feedbackRemarks",trade.getFeedbackRemarks());
        model.addAttribute("tradeCreateTime",sdf.format(trade.getTradeCreateTime()));
        model.addAttribute("tradeFinishTime",sdf.format(trade.getTradeFinishTime()));
        model.addAttribute("tradeStatus",trade.getTradeStatus() == 0 ? "unFinished" : "Finished");

        LinkedList<PageTradeDetail> pageTradeDetails = new LinkedList<PageTradeDetail>();
        List<TradeDetail> details = seller_sellerService.getTradeList(tradeId);
        for(TradeDetail tradeDetail : details)
        {
            PageTradeDetail pageTradeDetail = new PageTradeDetail();
            pageTradeDetail.setProductName(seller_sellerService.getProduct(tradeDetail.getProductId()).getProductName());
            pageTradeDetail.setAmount(tradeDetail.getProductAmount());
            pageTradeDetail.setProductPrice(tradeDetail.getProductTradePrice());
            pageTradeDetail.setTotalPrice(tradeDetail.getProductAmount() * tradeDetail.getProductTradePrice());
            pageTradeDetails.add(pageTradeDetail);
        }

        model.addAttribute("details",pageTradeDetails);
        model.addAttribute("SellerID",sellerID);
        return "/Seller/ViewTradeDetail";
    }

    private String getTradeStatus(int id)
    {
        if(id == 1)
        {
            return "finished";
        }
        return "not finish";
    }

    @RequestMapping("ReturnBack")
    public String jumpToLastLayer(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/Main";
    }

    @RequestMapping("ReturnBack1")
    public String jumpToViewTransation(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ViewTransaction/ViewTransactionHandler";
    }

}
