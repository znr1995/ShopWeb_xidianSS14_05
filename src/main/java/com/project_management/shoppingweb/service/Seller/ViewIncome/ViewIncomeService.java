package com.project_management.shoppingweb.service.Seller.ViewIncome;

import com.project_management.shoppingweb.domain.Orderinfo;
import com.project_management.shoppingweb.service.Seller.ViewTranstion.ViewTranstionService;
import org.hibernate.criterion.Order;

import java.util.Date;
import java.util.LinkedList;

public class ViewIncomeService extends  ViewTranstionService {

    ViewIncomeService(long sellerId) {
        super(sellerId);
    }

    public LinkedList<Orderinfo> getFinishedOrderByDate(Date startDate, Date endDate) {
        LinkedList<Orderinfo> orders = getFinishedOrder();
        LinkedList<Orderinfo> retOrders = new LinkedList<Orderinfo>();
        for (Orderinfo curOrder: orders ) {
            if(startDate.before(curOrder.getOrderFinishTime()) && endDate.after(curOrder.getOrderFinishTime()))
            {
                retOrders.add(curOrder);
            }
        }
        return retOrders;
    }

    public double getSumMoneyOfOrders(LinkedList<Orderinfo> orders)
    {
        double sumValue = 0.0;
        for(Orderinfo curOrder : orders)
        {
            sumValue += curOrder.getOrderTotalMoney();
        }
        return  sumValue;
    }
}
