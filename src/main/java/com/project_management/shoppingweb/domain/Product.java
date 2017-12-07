package com.project_management.shoppingweb.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "t_product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private int sellerId;
	private String brandName;
	private int firstPageModule;
	private Boolean isOnSale;
	private String productBriefInfo;
	private double productMarketPrice;
	private String productName;
	private String productPhoto;
	private int productStock;
	private double productTradePrice;


	private String size;
	int type;

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}





	public Boolean getOnSale() {
		return isOnSale;
	}

	public void setOnSale(Boolean onSale) {
		isOnSale = onSale;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public int getFirstPageModule() {
		return firstPageModule;
	}
	public void setFirstPageModule(int firstPageModule) {
		this.firstPageModule = firstPageModule;
	}
	public Boolean getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(Boolean isOnSale) {
		this.isOnSale = isOnSale;
	}
	public String getProductBriefInfo() {
		return productBriefInfo;
	}
	public void setProductBriefInfo(String productBriefInfo) {
		this.productBriefInfo = productBriefInfo;
	}
	public double getProductMarketPrice() {
		return productMarketPrice;
	}
	public void setProductMarketPrice(double productMarketPrice) {
		this.productMarketPrice = productMarketPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	public double getProductTradePrice() {
		return productTradePrice;
	}
	public void setProductTradePrice(double productTradePrice) {
		this.productTradePrice = productTradePrice;
	}
	public String getSize() {
		return size;
	}

}
