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
@Table(name = "t_orderinfo")
public class Orderinfo {
	
	private int orderId;
	private String addressName;
	private String deliveryAddress;
	private String feedbackRemarks;
	private Date orderCreateTime;
	private String orderPayWay;
	private int orderStatus;
	private double orderTotalMoney;
	private String phone;
	private List<Orderdetail> orderdetails;
	private User user;
	private Seller seller;



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getFeedbackRemarks() {
		return feedbackRemarks;
	}
	public void setFeedbackRemarks(String feedbackRemarks) {
		this.feedbackRemarks = feedbackRemarks;
	}
	public Date getOrderCreateTime() {
		return orderCreateTime;
	}
	public void setOrderCreateTime(Date orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}
	public String getOrderPayWay() {
		return orderPayWay;
	}
	public void setOrderPayWay(String orderPayWay) {
		this.orderPayWay = orderPayWay;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public double getOrderTotalMoney() {
		return orderTotalMoney;
	}
	public void setOrderTotalMoney(double orderTotalMoney) {
		this.orderTotalMoney = orderTotalMoney;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL},mappedBy="orderinfo")
	public List<Orderdetail> getOrderdetails() {
		return this.orderdetails;
	}

	public void setOrderdetails(List<Orderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}

	public Orderdetail addOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().add(orderdetail);
		orderdetail.setOrderinfo(this);

		return orderdetail;
	}

	public Orderdetail removeOrderdetail(Orderdetail orderdetail) {
		getOrderdetails().remove(orderdetail);
		orderdetail.setOrderinfo(null);

		return orderdetail;
	}
	
	@ManyToOne
	@JoinColumn(name="use_user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public Seller getSeller() {
		return seller;
	}
}
