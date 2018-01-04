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
    Long commission;
    Long sellerId;
    Long sellerName;
}
