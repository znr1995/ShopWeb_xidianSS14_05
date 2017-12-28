package com.project_management.shoppingweb.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "t_trade")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeId;
    private String addressId;
    private String feedbackRemarks;
    private Date tradeCreateTime;
    private Date tradeFinishTime;
    private String tradePayWay;
    private int tradeStatus;// 0 - 待处理订单 1-等待发货 2-等待收货 3-完成 4-发起退货等待确认 5-退货成功
    private double tradeTotalMoney;
    private Long userId;
    private Long sellerId;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }


    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getFeedbackRemarks() {
        return feedbackRemarks;
    }

    public void setFeedbackRemarks(String feedbackRemarks) {
        this.feedbackRemarks = feedbackRemarks;
    }

    public Date getTradeCreateTime() {
        return tradeCreateTime;
    }

    public void setTradeCreateTime(Date tradeCreateTime) {
        this.tradeCreateTime = tradeCreateTime;
    }

    public Date getTradeFinishTime() {
        return tradeFinishTime;
    }

    public void setTradeFinishTime(Date tradeFinishTime) {
        this.tradeFinishTime = tradeFinishTime;
    }

    public String getTradePayWay() {
        return tradePayWay;
    }

    public void setTradePayWay(String tradePayWay) {
        this.tradePayWay = tradePayWay;
    }

    public int getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public double getTradeTotalMoney() {
        return tradeTotalMoney;
    }

    public void setTradeTotalMoney(double tradeTotalMoney) {
        this.tradeTotalMoney = tradeTotalMoney;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
