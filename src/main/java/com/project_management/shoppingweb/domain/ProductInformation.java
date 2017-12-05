package com.project_management.shoppingweb.domain;

public class ProductInformation {
     /*
  {
  	productId	:	long,
  	productName	:	String,
  	productNum	:	long,			//
  	productPrice:	long,			//产品价格，判断合法
  	JSONStr		:	String,			//产品其他属性
  	productPicture:	String,			//产品图片
  	productNote	:	String
  }
  */

     private long productId;
     private long seller;
     private String productName;
     private long productNumber;
     private double productPrice;
     private String JSONStr;
     private String productPicture;
     private String productNote;

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductNumber(long productNumber) {
        this.productNumber = productNumber;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setJSONStr(String JSONStr) {
        this.JSONStr = JSONStr;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public void setProductNote(String productNote) {
        this.productNote = productNote;
    }

    public long getProductId() {

        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getProductNumber() {
        return productNumber;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public String getJSONStr() {
        return JSONStr;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setSeller(long seller) {
        this.seller = seller;
    }

    public long getSeller() {

        return seller;
    }

    public ProductInformation(long productId, long seller, String productName, long productNumber, double productPrice, String JSONStr, String productPicture, String productNote) {
        this.productId = productId;
        this.seller = seller;
        this.productName = productName;
        this.productNumber = productNumber;
        this.productPrice = productPrice;
        this.JSONStr = JSONStr;
        this.productPicture = productPicture;
        this.productNote = productNote;
    }

    public String getProductNote() {
        return productNote;
    }
}
