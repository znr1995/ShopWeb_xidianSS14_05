package com.project_management.shoppingweb.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_Advertisement")
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adId;
    private  int sellerId;

    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    //其余属性后期添加
}
