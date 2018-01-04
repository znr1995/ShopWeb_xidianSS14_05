package com.project_management.shoppingweb.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;
    private Date date;
    private Long commission;
    private Long sellerId;
    private String sellerName;
    private String type;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCommission() {
        return commission;
    }

    public void setCommission(Long commission) {
        this.commission = commission;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
