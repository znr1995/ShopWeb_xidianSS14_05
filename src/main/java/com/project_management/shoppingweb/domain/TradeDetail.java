package com.project_management.shoppingweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_tradedetail")
public class TradeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tradeDetailId;
    private Long tradeId;
    private int productAmount;
    private int productId;
    private double productTradePrice;

    public Long getTradeDetailId() {
        return tradeDetailId;
    }

    public void setTradeDetailId(Long tradeDetailId) {
        this.tradeDetailId = tradeDetailId;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getProductTradePrice() {
        return productTradePrice;
    }

    public void setProductTradePrice(double productTradePrice) {
        this.productTradePrice = productTradePrice;
    }
}
