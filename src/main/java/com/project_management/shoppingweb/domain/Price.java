package com.project_management.shoppingweb.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_price")
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long priceId;
	private Long adminId;
	private double advertisementHighPrice;
	private double advertisementLowPrice;
	private double shopPrice;
	private double productRate;
	private Date createTime;
	
	public Long getPriceId() {
		return priceId;
	}
	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	public double getAdvertisementHighPrice() {
		return advertisementHighPrice;
	}
	public void setAdvertisementHighPrice(double advertisementHighPrice) {
		this.advertisementHighPrice = advertisementHighPrice;
	}
	public double getAdvertisementLowPrice() {
		return advertisementLowPrice;
	}
	public void setAdvertisementLowPrice(double advertisementLowPrice) {
		this.advertisementLowPrice = advertisementLowPrice;
	}
	public double getShopPrice() {
		return shopPrice;
	}
	public void setShopPrice(double shopPrice) {
		this.shopPrice = shopPrice;
	}
	public double getProductRate() {
		return productRate;
	}
	public void setProductRate(double productRate) {
		this.productRate = productRate;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
