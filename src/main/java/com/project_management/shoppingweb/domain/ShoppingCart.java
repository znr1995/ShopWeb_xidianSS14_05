package com.project_management.shoppingweb.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "t_shopping_cart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long shoppingcartId;
	private int productAmount;
	private Long productId;
	private Date createtime;
	private Long userId;

	public Long getShoppingcartId() {
		return shoppingcartId;
	}

	public void setShoppingcartId(Long shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
