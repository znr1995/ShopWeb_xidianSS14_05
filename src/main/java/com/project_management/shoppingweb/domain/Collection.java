package com.project_management.shoppingweb.domain;


import javax.persistence.*;

@Entity
@Table(name = "t_collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int collectionId;
    int userId;
    int productId;
}
