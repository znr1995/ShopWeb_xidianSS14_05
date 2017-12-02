package com.project_management.shoppingweb.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_address")
public class Seller {
     /*商家JSON格式：
  {	(名称，类型)
	sellId	:	long，
	username:	String,
    email	:	String,
    passwd	:	String,
    phoneNum:	String,
    adress	:	String,
    sculpture:	String //(头像：图片的路径)
  }
  */
     private long sellerId;
     private String username;
     private String email;
     private String passwd;
     private String phoneNum;
     private String address;
     private String sculpture;

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSculpture(String sculpture) {
        this.sculpture = sculpture;
    }

    public long getSellerId() {

        return sellerId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public String getSculpture() {
        return sculpture;
    }

    public Seller(long sellerId, String username, String email, String passwd, String phoneNum, String address, String sculpture) {
        this.sellerId = sellerId;
        this.username = username;
        this.email = email;
        this.passwd = passwd;
        this.phoneNum = phoneNum;
        this.address = address;
        this.sculpture = sculpture;
    }
}
