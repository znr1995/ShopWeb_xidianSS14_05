package com.project_management.shoppingweb.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "t_address")
public class Address {

	private Long addressId;
	private String addressName;
	private String city;
	private String detailAddress;
	private String district;
	private Boolean isDefaultAddress;
	private String tel;
	private int postcode;
	private String province;
	private Long userId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public Boolean getIsDefaultAddress() {
		return isDefaultAddress;
	}
	public void setIsDefaultAddress(Boolean isDefaultAddress) {
		this.isDefaultAddress = isDefaultAddress;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getPostcode() {
		return postcode;
	}
	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getDefaultAddress() {

		return isDefaultAddress;
	}

	public void setDefaultAddress(Boolean defaultAddress) {
		isDefaultAddress = defaultAddress;
	}
}
