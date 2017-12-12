package com.project_management.shoppingweb.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_advertisement")
public class SellerAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long advertisementId;
	private Long sellerId;
	private String sellerName;
	private String description;
	private String pictureUrl;
	private Date startDate;//*****************
	private Date endDate;//通过这个判断是否可以放在主页*********
	private double price;
	private Integer status;// 1 - 未判断， 0 - 通过

	public Long getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Long advertisementId) {
		this.advertisementId = advertisementId;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
}
