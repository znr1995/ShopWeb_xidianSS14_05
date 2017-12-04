package com.project_management.shoppingweb.service.Seller.ViewTranstion;

import com.project_management.shoppingweb.domain.Orderinfo;
import com.project_management.shoppingweb.service.SellerSQLFunction;

import java.util.LinkedList;

public class ViewTranstionService {
    private long sellerId;
    private LinkedList<Orderinfo> orderlist;
    SellerSQLFunction sellerFunction = new SellerSQLFunction();

    private int FINISH_ORDER = 1;
    private int UNFINISH_ORDER = 2;

    public ViewTranstionService(long sellerId)
    {
        this.sellerId = sellerId;
        this.orderlist = sellerFunction.getTrade(sellerId,0);  // 0 -> all of order's
    }

     public LinkedList<Orderinfo> getFinishedOrder()
    {
        LinkedList<Orderinfo> finishedOrder = new LinkedList<Orderinfo>();
        for (Orderinfo currentOrder : orderlist) {
            if(currentOrder.getOrderStatus() == FINISH_ORDER )
                finishedOrder.add(currentOrder);
        }
        return  finishedOrder;
    }

    public LinkedList<Orderinfo> getUnfinishedOrder()
    {
        LinkedList<Orderinfo> unfinishedOrder = new LinkedList<Orderinfo>();
        for (Orderinfo currentOrder : orderlist) {
            if(currentOrder.getOrderStatus() == UNFINISH_ORDER )
                unfinishedOrder.add(currentOrder);
        }
        return  unfinishedOrder;
    }



}
