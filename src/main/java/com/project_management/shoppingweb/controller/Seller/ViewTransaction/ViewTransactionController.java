package com.project_management.shoppingweb.controller.Seller.ViewTransaction;

import com.project_management.shoppingweb.domain.Product;
import com.project_management.shoppingweb.domain.Trade;
import com.project_management.shoppingweb.domain.TradeDetail;
import com.project_management.shoppingweb.service.Seller.Seller_SellerService;
import org.hibernate.boot.spi.InFlightMetadataCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sun.reflect.annotation.ExceptionProxy;

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
        List<Trade> trades = seller_sellerService.getTradeList(sellerId,0);
        trades.addAll(seller_sellerService.getTradeList(sellerId,1));
        trades.addAll(seller_sellerService.getTradeList(sellerId,2));

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
        return "/Seller/ViewTransaction";
    }

    //搜索
    @RequestMapping("Search")
    public String getTradesByDate(@RequestParam("StartDate")String startDate,
                                  @RequestParam("EndDate")String endDate,
                                  @RequestParam("type")int status,
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

        List<Trade> trades = seller_sellerService.getTradeListByTime(sellerId,status,start,end);
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
        String startDate = "NULL", endDate = "NULL";
        model.addAttribute("userName",seller_sellerService.getSellerById(trade.getSellerId()).getUsername());
        model.addAttribute("tradePayWay",trade.getTradePayWay());
        model.addAttribute("tradeTotalMoney",trade.getTradeTotalMoney());
        model.addAttribute("address",seller_sellerService.getAddressString(Long.valueOf(trade.getAddressId())));
        model.addAttribute("feedbackRemarks",trade.getFeedbackRemarks());
        model.addAttribute("tradeID",trade.getTradeId());
        try {
            startDate =sdf.format(trade.getTradeCreateTime());
        }catch (Exception e){}

        try {
            endDate =sdf.format(trade.getTradeFinishTime());
        }catch (Exception e){}

        model.addAttribute("tradeCreateTime",startDate);
        model.addAttribute("tradeFinishTime",endDate);
        model.addAttribute("tradeStatus",getTradeStatus(trade.getTradeStatus()));
        model.addAttribute("status",trade.getTradeStatus());

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

    @RequestMapping("dispose")
    public String disposeTrade(HttpServletRequest request,RedirectAttributes attributes)
    {
       long tradeId = Long.valueOf(request.getParameter("tradeID"));
       long sellerId = Long.valueOf(request.getParameter("SellerID"));
       int status = Integer.valueOf(request.getParameter("status"));
       Trade trade = seller_sellerService.getTrade(tradeId);
       if(trade == null)
       {
           attributes.addAttribute("error","trade id not valid!");
           return "redirect:/error/errorHandler";
       }

        // 0 - 待处理订单 1-等待发货 2-等待收货 3-完成 4-发起退货等待确认 5-退货成功 6-申诉
        // 0,1,4需要确认
        //如果状态是4，需要把对应的商品数量加上
        if(status == 4)
        {
            List<TradeDetail> tradeDetails = seller_sellerService.getTradeList(tradeId);
            for(TradeDetail tradeDetail : tradeDetails)
            {
                Product product = seller_sellerService.getProduct(tradeDetail.getProductId());
                product.setProductStock(product.getProductStock() + tradeDetail.getProductAmount());
            }
        }

       trade.setTradeStatus(status+1);
       seller_sellerService.writeInTrade(trade);
       attributes.addAttribute("SellerID",sellerId);
       return "redirect:/Seller/ViewTransaction/ViewTransactionHandler";
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

    @RequestMapping("ReturnBack1")
    public String jumpToViewTransation(RedirectAttributes attributes)
    {
        attributes.addAttribute("SellerID",sellerID);
        return "redirect:/Seller/ViewTransaction/ViewTransactionHandler";
    }

}
