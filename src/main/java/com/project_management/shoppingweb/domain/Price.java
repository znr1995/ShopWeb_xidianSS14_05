package com.project_management.shoppingweb.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_price")
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long priceId;
	private Long adminId;
	private double productHighAdvertisementPrice;//商品列表广告
	private double productLowAdvertisementPrice;//商品滚动广告
	private double sellerListAdvertisementPrice;//店铺列表广告
	private double shopPrice;//开店价格
	private double productRate;//商品汇率
	 @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;//修改时间

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public double getProductHighAdvertisementPrice() {
		return productHighAdvertisementPrice;
	}

	public void setProductHighAdvertisementPrice(double productHighAdvertisementPrice) {
		this.productHighAdvertisementPrice = productHighAdvertisementPrice;
	}

	public double getProductLowAdvertisementPrice() {
		return productLowAdvertisementPrice;
	}

	public void setProductLowAdvertisementPrice(double productLowAdvertisementPrice) {
		this.productLowAdvertisementPrice = productLowAdvertisementPrice;
	}

	public double getSellerListAdvertisementPrice() {
		return sellerListAdvertisementPrice;
	}

	public void setSellerListAdvertisementPrice(double sellerListAdvertisementPrice) {
		this.sellerListAdvertisementPrice = sellerListAdvertisementPrice;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
