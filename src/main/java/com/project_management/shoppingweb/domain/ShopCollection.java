
package com.project_management.shoppingweb.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_shopCollection")
public class ShopCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Long sellerId;
    Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

