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
	private Long productId;
	private Long sellerId;
	private String brandName;//种类
	//private int firstPageModule;
	//private Boolean isOnSale;
	private String productBriefInfo;//简介
	//private double productMarketPrice;
	private String productName;
	private String productPhoto;
	private Integer productStock;//存货
	private double productPrice;//价格


	private String size;
	private String type;

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}





/*	public Boolean getOnSale() {
		return isOnSale;
	}

	public void setOnSale(Boolean onSale) {
		isOnSale = onSale;
	}
*/

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
/*	public int getFirstPageModule() {
		return firstPageModule;
	}
	public void setFirstPageModule(int firstPageModule) {
		this.firstPageModule = firstPageModule;
	}*/
/*	public Boolean getIsOnSale() {
		return isOnSale;
	}
	public void setIsOnSale(Boolean isOnSale) {
		this.isOnSale = isOnSale;
	}*/
	public String getProductBriefInfo() {
		return productBriefInfo;
	}
	public void setProductBriefInfo(String productBriefInfo) {
		this.productBriefInfo = productBriefInfo;
	}
	/*public double getProductMarketPrice() {
		return productMarketPrice;
	}
	public void setProductMarketPrice(double productMarketPrice) {
		this.productMarketPrice = productMarketPrice;
	}*/
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

	public void setSize(String size) {
		this.size = size;
	}


	public String getSize() {
		return size;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}



}
