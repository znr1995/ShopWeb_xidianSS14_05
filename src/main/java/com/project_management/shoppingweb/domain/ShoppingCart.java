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

	private int shoppingcartId;
	private int productAmount;
	private Product product;
	private Date createtime;
	private User user;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getShoppingcartId() {
		return shoppingcartId;
	}
	public void setShoppingcartId(int shoppingcartId) {
		this.shoppingcartId = shoppingcartId;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	
	@ManyToOne
	@JoinColumn(name="pro_product_id")
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	@ManyToOne
	@JoinColumn(name="use_user_id")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}
