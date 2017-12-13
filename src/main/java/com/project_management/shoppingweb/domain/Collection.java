package com.project_management.shoppingweb.domain;


import javax.persistence.*;

@Entity
@Table(name = "t_collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long collectionId;
    Long userId;
    Long productId;
	public Long getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
    
    
}
