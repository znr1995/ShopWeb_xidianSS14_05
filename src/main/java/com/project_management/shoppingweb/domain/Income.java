package com.project_management.shoppingweb.domain;

import javax.persistence.*;

@Entity
@Table(name = "t_income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    String year;
    String month;
    String day;
    String type; //"rate"佣金，“shop”开店，“advertisement”广告
    Long commission;
    Long sellerId;
    Long sellerName;

}
