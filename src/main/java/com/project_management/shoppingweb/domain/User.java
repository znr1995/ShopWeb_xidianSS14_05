package com.project_management.shoppingweb.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "t_user")
public class User {

	
	private Long id;
	private String username;
	private String password;
	private String email;
	private String tel;
	private List<Address> addresses;
	private List<ShoppingCart> shoppingcarts;
	private List<Orderinfo> orderinfos;
	private Date createtime;
	private boolean enabled;
	private Integer sex;// 1 - 男， 2 - 女
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@OneToMany(mappedBy="user")
	public List<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setUser(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setUser(null);

		return address;
	}
	
	@OneToMany(mappedBy="user")
	public List<Orderinfo> getOrderinfos() {
		return this.orderinfos;
	}

	public void setOrderinfos(List<Orderinfo> orderinfos) {
		this.orderinfos = orderinfos;
	}

	public Orderinfo addOrderinfo(Orderinfo orderinfo) {
		getOrderinfos().add(orderinfo);
		orderinfo.setUser(this);

		return orderinfo;
	}

	public Orderinfo removeOrderinfo(Orderinfo orderinfo) {
		getOrderinfos().remove(orderinfo);
		orderinfo.setUser(null);

		return orderinfo;
	}
	
	@OneToMany(mappedBy="user")
	public List<ShoppingCart> getShoppingcarts() {
		return this.shoppingcarts;
	}

	public void setShoppingcarts(List<ShoppingCart> shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

	public ShoppingCart addShoppingcart(ShoppingCart shoppingcart) {
		getShoppingcarts().add(shoppingcart);
		shoppingcart.setUser(this);

		return shoppingcart;
	}

	public ShoppingCart removeShoppingcart(ShoppingCart shoppingcart) {
		getShoppingcarts().remove(shoppingcart);
		shoppingcart.setUser(null);

		return shoppingcart;
	}
	
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}

}
