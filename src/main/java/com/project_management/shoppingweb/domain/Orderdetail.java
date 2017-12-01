package com.project_management.shoppingweb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "t_orderdetail")
public class Orderdetail {

	
	private int orderDetailId;
	private int productAmount;
	private int productId;
	private String productPhoto;
	private double productTradePrice;
	private Orderinfo orderinfo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(int orderDetailId) {
		this.orderDetailId = orderDetailId;
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
	public String getProductPhoto() {
		return productPhoto;
	}
	public void setProductPhoto(String productPhoto) {
		this.productPhoto = productPhoto;
	}
	public double getProductTradePrice() {
		return productTradePrice;
	}
	public void setProductTradePrice(double productTradePrice) {
		this.productTradePrice = productTradePrice;
	}
	@ManyToOne
	@JoinColumn(name="ord_order_id")
	public Orderinfo getOrderinfo() {
		return orderinfo;
	}
	public void setOrderinfo(Orderinfo orderinfo) {
		this.orderinfo = orderinfo;
	}
	
	
}
